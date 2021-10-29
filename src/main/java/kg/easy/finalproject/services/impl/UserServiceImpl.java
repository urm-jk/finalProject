package kg.easy.finalproject.services.impl;

import io.jsonwebtoken.*;
import kg.easy.finalproject.dao.UserRepository;
import kg.easy.finalproject.enums.CodeStatus;
import kg.easy.finalproject.mappers.CodeMapper;
import kg.easy.finalproject.mappers.UserMapper;
import kg.easy.finalproject.models.dto.CodeDto;
import kg.easy.finalproject.models.entities.Code;
import kg.easy.finalproject.models.responses.ErrorResponse;
import kg.easy.finalproject.models.dto.UserDto;
import kg.easy.finalproject.models.entities.User;
import kg.easy.finalproject.models.responses.OkResponse;
import kg.easy.finalproject.models.responses.SuccessLogin;
import kg.easy.finalproject.services.CodeService;
import kg.easy.finalproject.services.RequestService;
import kg.easy.finalproject.services.SendMailService;
import kg.easy.finalproject.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

//@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CodeService codeService;

    @Autowired
    RequestService requestService;
    @Autowired
    UserService userService;

    @Value("${jwtSecret")
    String secretKey;

    @Override
    public ResponseEntity<?> saveUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.mapToUser(userDto);
        if (Objects.isNull(userRepository.findUserByLogin(user.getLogin()))) {
            userRepository.save(user);
        } else {
            return new ResponseEntity<>(
                    new ErrorResponse("Пользователь уже существует", null),
                    HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(UserMapper.INSTANCE.mapToUserDto(user));

    }

    @Override
    public boolean userLockOutChecking(User user) {
        if (Objects.nonNull(user.getBlockDate())) {
            if (new Date().before(user.getBlockDate())){
            return true;
        }
    }
    return false;
    }

//    @Override
//    public User findUserByLogin(String login){
//        return userRepository.findByLogin(login);
//    }

    @Override
    public ResponseEntity<?> sendCode(String login) {
        User user = userRepository.findUserByLogin(login);
        if (Objects.isNull(user)){
            return new ResponseEntity<>(new ErrorResponse("Логин не найден", null), HttpStatus.NOT_FOUND);
        }
        boolean check = userLockOutChecking(user);
        if (check){
            SimpleDateFormat formatToShowEndOfBlockDate = new SimpleDateFormat("hh:mm a");
            return new ResponseEntity<>("Превышено количество попыток входа, вы заблокирован. повторите попытку в " + formatToShowEndOfBlockDate.format(user.getBlockDate()),HttpStatus.CONFLICT);}

        codeService.sendCode(UserMapper.INSTANCE.mapToUserDto(user));
        return ResponseEntity.ok(new OkResponse("Код подтверждения успешно отправлен", null));
    }



    @Override
    public ResponseEntity<?> getToken(String login, String code) {
        User user = userRepository.findUserByLogin(login);
        if (Objects.isNull(user)){
            return new ResponseEntity<>(new ErrorResponse("Неккоректный логин", null), HttpStatus.NOT_FOUND);
        }
        boolean check =userLockOutChecking(user);
        if (check) {
            SimpleDateFormat formatToShowEndOfBlockDate = new SimpleDateFormat("hh:mm a");
            return new ResponseEntity<>("Первышено количество попыток входа. Вы заблокированы на 1час" + formatToShowEndOfBlockDate.format(user.getBlockDate()), HttpStatus.CONFLICT);
        }
        CodeDto checkUserCode = CodeMapper.INSTANCE.mapToCodeDto(codeService.findLastCode(UserMapper.INSTANCE.mapToUserDto(user)));
        if (new Date().after(checkUserCode.getEndDate())){
            return new ResponseEntity<>(new ErrorResponse("Время действия кода подтверждения истекло", "Повторите попытку повторно"), HttpStatus.CONFLICT);
        }
        if (!BCrypt.checkpw(code, checkUserCode.getCode())){
            requestService.saveRequest(checkUserCode,false);
            if (requestService.countFailedAttempts(checkUserCode)>=3){
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, 1);

                user.setBlockDate(calendar.getTime());
                userRepository.save(user);

                checkUserCode.setCodeStatus(CodeStatus.FAILED);
                codeService.saveCode(checkUserCode);
            }
            return new ResponseEntity<>(new ErrorResponse("Авторизация не пройдена", "Вы ввели неккорекстный код подтверждения"), HttpStatus.NOT_FOUND);
        }

        requestService.saveRequest(checkUserCode, true);

        Calendar tokensTimeLive = Calendar.getInstance();
        tokensTimeLive.add(Calendar.HOUR, 3);

        String token = Jwts.builder().claim("login", login).setExpiration(tokensTimeLive.getTime()).signWith(SignatureAlgorithm.HS256, secretKey).compact();
        checkUserCode.setCodeStatus(CodeStatus.APPROVED);
        codeService.saveCode(checkUserCode);
        SuccessLogin successLogin = new SuccessLogin("Вы успешно ввели пароль", token);
        return ResponseEntity.ok(successLogin);
    }


    @Override
    public ResponseEntity<?> verifyLogin(String token) {
        try {
                Jws<Claims> jwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
                return ResponseEntity.ok(jwt.getBody().get("login"));
        }catch (ExpiredJwtException jwtException){
            return new ResponseEntity<>("Время действия токена истек", HttpStatus.CONFLICT);
        }catch (UnsupportedJwtException jwtException){
            return new ResponseEntity<>("Неподерживаемый токен", HttpStatus.CONFLICT);
        }catch (MalformedJwtException jwtException){
            return new ResponseEntity<>("Неккореткный токен", HttpStatus.CONFLICT);
        }catch (SignatureException signatureException){
            return new ResponseEntity<>("Неккоректная подпись в токене", HttpStatus.CONFLICT);
        }catch (Exception exception){
            return new ResponseEntity<>("Несанкциронированный токен", HttpStatus.CONFLICT);
        }


    }


}
