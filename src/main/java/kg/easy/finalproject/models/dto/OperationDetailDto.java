package kg.easy.finalproject.models.dto;

import kg.easy.finalproject.models.entities.Operation;
import kg.easy.finalproject.models.entities.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class OperationDetailDto {
    Long id;
    double amount;
    double price;
    Product product;
    Operation operation;

}
