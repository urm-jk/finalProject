package kg.easy.finalproject.services.impl;

import kg.easy.finalproject.dao.CategoryRepository;
import kg.easy.finalproject.mappers.CategoryMapper;
import kg.easy.finalproject.models.dto.CategoryDto;
import kg.easy.finalproject.models.entities.Category;
import kg.easy.finalproject.models.responses.ErrorResponse;
import kg.easy.finalproject.services.CategoryService;
import kg.easy.finalproject.services.UserService;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    UserService userService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> saveCategory(String token, CategoryDto categoryDto) {

        ResponseEntity<?>responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Category category = CategoryMapper.INSTANCE.mapToCategory(categoryDto);
        if (Objects.isNull(categoryRepository.findByName(category.getName()))){
            categoryRepository.save(category);
        }
        return ResponseEntity.ok(CategoryMapper.INSTANCE.mapToCategoryDto(category));
    }

    @Override
    public ResponseEntity<?> getByName(String token, String name) {
        ResponseEntity<?>responseEntity = userService.verifyLogin(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Category category = categoryRepository.findByName(name);
        if (Objects.nonNull(category)){
            return new ResponseEntity<>(new ErrorResponse("Категория товаров не найдена", null), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(CategoryMapper.INSTANCE.mapToCategoryDto(category));
    }
    @SneakyThrows
    @Override
    public ResponseEntity<?> getAllCategories(String token) {
        ResponseEntity<?>responseEntity = userService.verifyLogin(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        List<Category> categoryList=categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream().map(x->CategoryMapper.INSTANCE.mapToCategoryDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok(categoryDtoList);
    }
}
