package kg.easy.finalproject.models.dto;

import kg.easy.finalproject.models.entities.Category;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String name;
    String barcode;
    boolean active;
    CategoryDto category;
}
