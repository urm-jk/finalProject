package kg.easy.finalproject.services;

import kg.easy.finalproject.models.dto.UserDto;
import kg.easy.finalproject.models.entities.User;
import org.springframework.http.ResponseEntity;


public interface UserService {
    ResponseEntity<?> saveUser(UserDto userDto);

    ResponseEntity<?> sendCode(String login);

    ResponseEntity<?> getToken(String login, String code);

    ResponseEntity<?> verifyLogin (String token);

    boolean userLockOutChecking(User user);

//    User findUserByLogin(String login);
}
