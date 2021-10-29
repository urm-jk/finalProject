package kg.easy.finalproject.models.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReceiptDto {
    List<ReceiptDetailsDto> receipDatailsDto;
    double totalAmount;
    String cashier;

}
