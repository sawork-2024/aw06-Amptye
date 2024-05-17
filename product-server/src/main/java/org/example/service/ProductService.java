package org.example.service;

import org.example.model.Category;
import org.example.model.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    public Collection<Product> findAllProducts();
    public Product findProductById(long id);
    public Product findProductByPid(String pid);
    public List<Product> findProductsByName(String name);
    public void deleteProduct(Product product);
    public void saveProduct(Product product);
    public Collection<Category> findAllCategories();
    public Category findCategoryById(long id);
    public List<Category> findCategoriesByName(String name);
    public void deleteCategory(Category category);
    public void saveCategory(Category category);
}
