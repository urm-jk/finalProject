package kg.easy.finalproject.models.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReceiptDetailsDto {
    String name;
    String barcode;
    int quantity;
    double price;
    double discount;
    double amount;

}
