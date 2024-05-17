package org.example.rest;

import org.example.mapper.CategoryMapper;
import org.example.service.ProductService;
import org.example.mapper.ProductMapper;
import org.example.model.Category;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.micropos.api.ProductsApi;
import com.micropos.dto.PatchProductRequestDto;
import com.micropos.dto.ProductDto;
import com.micropos.dto.ProductFieldsDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class ProductController implements ProductsApi {
    private final ProductService productService;

    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ResponseEntity<List<ProductDto>> listProducts() {
        ProductsApi.super.listProducts();
        List<ProductDto> products = new ArrayList<>(productMapper.toProductDtos(this.productService.findAllProducts()));
//        if (products.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> showProductById(Long productId) {
        Product product = this.productService.findProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productMapper.toProductDto(product), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> addProduct(ProductFieldsDto productFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Category category = null;
        if(productFieldsDto.getCategory() != null) {
            ///////////////////////////////////////////////////////////////
            category = this.productService.findCategoryById(productFieldsDto.getCategory().getId());
            //category = categoryMapper.toCategory(productFieldsDto.getCategory());
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        Product product = productMapper.toProduct(productFieldsDto);
        product.setCategory(category);
        if(productFieldsDto.getQuantity() == null){
            product.setQuantity(1);
        } else {
            product.setQuantity(productFieldsDto.getQuantity());
        }
        if(product.getQuantity() <= 0){
            product.setStock(false);
            product.setQuantity(0);
        } else {
            product.setStock(true);
        }
        this.productService.saveProduct(product);
        ProductDto productDto = productMapper.toProductDto(product);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/products/{id}")
                .buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(productDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductDto> deleteProduct(Long productId) {
        Product product = this.productService.findProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.productService.deleteProduct(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(Long productId, ProductFieldsDto productFieldsDto) {
        Product currentProduct = this.productService.findProductById(productId);
        if (currentProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentProduct.setName(productFieldsDto.getName());
        if(productFieldsDto.getPrice() != null){
            currentProduct.setPid(productFieldsDto.getPid());
        }
        if(productFieldsDto.getImage() != null) {
            currentProduct.setImage(productFieldsDto.getImage());
        }
        if(productFieldsDto.getPrice() != null) {
            currentProduct.setPrice(productFieldsDto.getPrice());
        }
        if(productFieldsDto.getQuantity() != null){
            currentProduct.setQuantity(productFieldsDto.getQuantity());
        }
        this.productService.saveProduct(currentProduct);
        return new ResponseEntity<>(productMapper.toProductDto(currentProduct), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> showProductByName(String productName) {
        List<Product> products = this.productService.findProductsByName(productName);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productMapper.toProductDtos(products), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> searchProductByName(String productName) {
        List<Product> products = this.productService.findProductsByName(productName);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productMapper.toProductDtos(products), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> patchProduct(Long productId, PatchProductRequestDto patchProductRequestDto) {
        Product currentProduct = this.productService.findProductById(productId);
        if (currentProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(patchProductRequestDto.getName() != null) {
            currentProduct.setName(patchProductRequestDto.getName());
        }
        if(patchProductRequestDto.getImage() != null) {
            currentProduct.setImage(patchProductRequestDto.getImage());
        }
        if(patchProductRequestDto.getPrice() != null) {
            currentProduct.setPrice(patchProductRequestDto.getPrice());
        }
        if(patchProductRequestDto.getQuantity() != null){
            currentProduct.setQuantity(patchProductRequestDto.getQuantity());
        }
        this.productService.saveProduct(currentProduct);
        return new ResponseEntity<>(productMapper.toProductDto(currentProduct), HttpStatus.OK);
    }
}
