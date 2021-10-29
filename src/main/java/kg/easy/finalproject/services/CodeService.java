package kg.easy.finalproject.services;

import kg.easy.finalproject.models.dto.CodeDto;
import kg.easy.finalproject.models.dto.UserDto;
import kg.easy.finalproject.models.entities.Code;


public interface CodeService {
    void saveCode(CodeDto codeDto);
    Code findLastCode(UserDto userDto);
    void sendCode(UserDto userDto);

}
