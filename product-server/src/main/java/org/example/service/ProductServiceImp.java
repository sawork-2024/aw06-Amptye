package org.example.service;

import org.example.mapper.CategoryMapper;
import org.example.mapper.ProductMapper;
import org.example.model.Category;
import org.example.model.Product;
import org.example.repository.CategoryRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Service
@Component
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final String PRODUCT_URL = "http://product-server/";

    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = "products")
    //@Transactional(readOnly = true)
    public Collection<Product> findAllProducts() {
        return productRepository.findAll();
    }
    @Override
    @Cacheable(value = "products", key = "#id")
    public Product findProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(value = "products", key = "'pid-' + #pid")
    public Product findProductByPid(String pid) {
        List<Product> found = productRepository.findByPid(pid);
        if(found.isEmpty()) return null;
        return found.get(0);
    }
    @Override
    @Cacheable(value = "products", key = "'name-' + #name")
    public List<Product> findProductsByName(String name){
        return productRepository.findByName(name);
    }
    @Override
    @CacheEvict(value = "products", allEntries = true)
    //@Transactional
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
    @Override
    @CacheEvict(value = "products", allEntries = true)
    //@Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    @Cacheable(value = "categories")
    public Collection<Category> findAllCategories(){
        return categoryRepository.findAll();
    }
    @Override
    @Cacheable(value = "categories", key = "#id")
    public Category findCategoryById(long id){
        return categoryRepository.findById(id).orElse(null);
    }
    @Override
    @Cacheable(value = "categories", key = "'name-' + #name")
    public List<Category> findCategoriesByName(String name){
        return categoryRepository.findByName(name);
    }
    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }
    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }
}
