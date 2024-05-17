package org.example.mapper;

import com.micropos.dto.ProductDto;
import com.micropos.dto.ProductFieldsDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setPid( product.getPid() );
        productDto.setName( product.getName() );
        productDto.setPrice( product.getPrice() );
        productDto.setImage( product.getImage() );
        productDto.setCategory( categoryMapper.toCategoryDto( product.getCategory() ) );
        productDto.setQuantity( product.getQuantity() );
        productDto.setId( product.getId() );
        productDto.setStock( product.isStock() );

        return productDto;
    }

    @Override
    public Product toProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        if ( productDto.getId() != null ) {
            product.setId( productDto.getId() );
        }
        product.setPid( productDto.getPid() );
        product.setName( productDto.getName() );
        if ( productDto.getPrice() != null ) {
            product.setPrice( productDto.getPrice() );
        }
        product.setImage( productDto.getImage() );
        product.setCategory( categoryMapper.toCategory( productDto.getCategory() ) );
        if ( productDto.getQuantity() != null ) {
            product.setQuantity( productDto.getQuantity() );
        }
        if ( productDto.getStock() != null ) {
            product.setStock( productDto.getStock() );
        }

        return product;
    }

    @Override
    public Product toProduct(ProductFieldsDto productFieldsDto) {
        if ( productFieldsDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setPid( productFieldsDto.getPid() );
        product.setName( productFieldsDto.getName() );
        if ( productFieldsDto.getPrice() != null ) {
            product.setPrice( productFieldsDto.getPrice() );
        }
        product.setImage( productFieldsDto.getImage() );
        product.setCategory( categoryMapper.toCategory( productFieldsDto.getCategory() ) );
        if ( productFieldsDto.getQuantity() != null ) {
            product.setQuantity( productFieldsDto.getQuantity() );
        }

        return product;
    }

    @Override
    public List<ProductDto> toProductDtos(Collection<Product> productCollection) {
        if ( productCollection == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( productCollection.size() );
        for ( Product product : productCollection ) {
            list.add( toProductDto( product ) );
        }

        return list;
    }

    @Override
    public Collection<Product> toProducts(Collection<ProductDto> productDtos) {
        if ( productDtos == null ) {
            return null;
        }

        Collection<Product> collection = new ArrayList<Product>( productDtos.size() );
        for ( ProductDto productDto : productDtos ) {
            collection.add( toProduct( productDto ) );
        }

        return collection;
    }
}
