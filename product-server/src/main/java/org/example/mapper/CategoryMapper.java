package org.example.mapper;

import org.example.model.Category;
import org.mapstruct.Mapper;
import com.micropos.dto.CategoryDto;
import com.micropos.dto.CategoryFieldsDto;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);

    Category toCategory(CategoryFieldsDto categoryFieldsDto);

    List<CategoryDto> toCategoryDtos(Collection<Category> categoryCollection);

    Collection<Category> toCategorys(Collection<CategoryDto> categoryDtos);
}
