package kg.easy.finalproject.models.dto;

import kg.easy.finalproject.models.entities.Code;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestDto {
    Long Id;
    boolean success;
    Date addDate;
    Code code;
}
