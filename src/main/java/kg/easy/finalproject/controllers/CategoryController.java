package kg.easy.finalproject.controllers;

import kg.easy.finalproject.models.dto.CategoryDto;
import kg.easy.finalproject.services.CategoryService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryController {

    CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public ResponseEntity<?>saveCategory(@RequestHeader String token, @RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(token, categoryDto);
    }

    @GetMapping("/getByName")
    public ResponseEntity<?>getByName(@RequestHeader String token, @RequestParam String name){
        return categoryService.getByName(token, name);
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<?>getAllCategories(@RequestHeader String token){
        return categoryService.getAllCategories(token);
    }

}
