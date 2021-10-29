package kg.easy.finalproject.controllers;

import kg.easy.finalproject.models.dto.UserDto;
import kg.easy.finalproject.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @PostMapping("/sendCode")
    public ResponseEntity<?> sendCode(@RequestParam String login){
        return userService.sendCode(login);
    }

    @GetMapping("/login")
    public ResponseEntity<?> getToken(@RequestParam String login, @RequestParam String code){
        return userService.getToken(login, code);
    }
    @GetMapping("/verify")
    public ResponseEntity<?> verifyLogin(@RequestParam String token){
        return userService.verifyLogin(token);
    }


}
