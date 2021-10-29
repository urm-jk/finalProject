package kg.easy.finalproject.models.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InputDataForOperation {
    String barcode;
    int quantity;
}
