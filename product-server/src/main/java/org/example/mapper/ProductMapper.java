package org.example.mapper;

import org.example.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.micropos.dto.ProductDto;
import com.micropos.dto.ProductFieldsDto;
import com.micropos.dto.UserFieldsDto;

import java.util.Collection;
import java.util.*;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    ProductDto toProductDto(Product product);

    Product toProduct(ProductDto productDto);

    Product toProduct(ProductFieldsDto productFieldsDto);

    List<ProductDto> toProductDtos(Collection<Product> productCollection);

    Collection<Product> toProducts(Collection<ProductDto> productDtos);
}
