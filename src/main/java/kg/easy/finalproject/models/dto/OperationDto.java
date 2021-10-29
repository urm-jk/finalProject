package kg.easy.finalproject.models.dto;

import kg.easy.finalproject.models.entities.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDto {
    Long id;
    Date addDate;
    double totalPrice;
    double change;
    User user;
}
