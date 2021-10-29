package kg.easy.finalproject.services.impl;

import kg.easy.finalproject.dao.CodeRepository;
import kg.easy.finalproject.enums.CodeStatus;
import kg.easy.finalproject.mappers.CodeMapper;
import kg.easy.finalproject.mappers.UserMapper;
import kg.easy.finalproject.models.dto.CodeDto;
import kg.easy.finalproject.models.dto.UserDto;
import kg.easy.finalproject.models.entities.Code;
import kg.easy.finalproject.models.entities.User;
import kg.easy.finalproject.services.CodeService;
import kg.easy.finalproject.services.SendSimpleMessage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Objects;
@Service
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeServiceImpl implements CodeService {
    @Autowired
    CodeRepository codeRepository;

    @Autowired
    SendSimpleMessage sendMessage;

    @Override
    public void saveCode(CodeDto codeDto) {
        codeRepository.save(CodeMapper.INSTANCE.mapToCode(codeDto));
    }

    @Override
    public Code findLastCode(UserDto userDto) {

        return codeRepository.findByUserAndCodeStatus(UserMapper.INSTANCE.mapToUser(userDto), CodeStatus.NEW);
    }

    @Override
    public void sendCode(UserDto userDto) {
        Code lastCode = codeRepository.findByUserAndCodeStatus(UserMapper.INSTANCE.mapToUser(userDto), CodeStatus.NEW);
        if (Objects.nonNull(lastCode)){
            lastCode.setCodeStatus(CodeStatus.CANCELLED);
            codeRepository.save(lastCode);
        }
        int code = (int) ((Math.random()*9000)+1000);
        String hashedCode = BCrypt.hashpw(Integer.toString(code), BCrypt.gensalt());

        Calendar endOfCodeAction = Calendar.getInstance();
        endOfCodeAction.add(Calendar.MINUTE, 3);

        Code saveCode = new Code();
        saveCode.setCode(hashedCode);
        saveCode.setEndDate(endOfCodeAction.getTime());
        saveCode.setCodeStatus(CodeStatus.NEW);
        saveCode.setUser(UserMapper.INSTANCE.mapToUser(userDto));
        codeRepository.save(saveCode);

        sendMessage.sendSimpleMessage(userDto.getEmail(), Integer.toString(code));

    }


}
