package kg.easy.finalproject.models.dto;

import kg.easy.finalproject.enums.CodeStatus;
import kg.easy.finalproject.models.entities.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeDto {
    Long id;
    String code;
    Date startDate;
    Date endDate;
    CodeStatus codeStatus;
    UserDto user;


}
