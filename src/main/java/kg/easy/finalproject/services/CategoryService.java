package kg.easy.finalproject.services;

import kg.easy.finalproject.models.dto.CategoryDto;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> saveCategory(String token, CategoryDto categoryDto);

    ResponseEntity<?> getByName(String token, String name);

    ResponseEntity<?> getAllCategories(String token);
}
