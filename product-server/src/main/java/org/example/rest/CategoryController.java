package org.example.rest;

import org.example.service.ProductService;
import org.example.mapper.CategoryMapper;
import org.example.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.micropos.api.CategoriesApi;
import com.micropos.dto.CategoryDto;
import com.micropos.dto.CategoryFieldsDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class CategoryController implements CategoriesApi {
    private final ProductService productService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(ProductService productService, CategoryMapper categoryMapper) {
        this.productService = productService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = new ArrayList<>(categoryMapper.toCategoryDtos(this.productService.findAllCategories()));
//        if (categories.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDto> showCategoryById(Long categoryId) {
        Category category = this.productService.findCategoryById(categoryId);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryMapper.toCategoryDto(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDto> addCategory(CategoryFieldsDto categoryFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Category category = categoryMapper.toCategory(categoryFieldsDto);
        this.productService.saveCategory(category);
        CategoryDto categoryDto = categoryMapper.toCategoryDto(category);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/categorys/{id}")
                .buildAndExpand(category.getId()).toUri());
        return new ResponseEntity<>(categoryDto, headers, HttpStatus.CREATED);
    }
}
