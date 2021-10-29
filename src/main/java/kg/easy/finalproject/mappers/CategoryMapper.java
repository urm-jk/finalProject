package kg.easy.finalproject.mappers;

import kg.easy.finalproject.models.dto.CategoryDto;
import kg.easy.finalproject.models.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category mapToCategory(CategoryDto categoryDto);

    CategoryDto mapToCategoryDto(Category category);
}
