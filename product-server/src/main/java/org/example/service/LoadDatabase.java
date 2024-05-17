package org.example.service;

import org.example.model.Product;
import org.example.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    private final ProductService productService;

    @Autowired
    public LoadDatabase(ProductService productService){
        this.productService = productService;
    }

    @Bean
    CommandLineRunner initDatabase() {

        return args -> {
            Category category = new Category("drink");
            productService.saveCategory(category);
//            List<Product> products = posDB.getProducts();
//            for(Product product : products){
//                productService.saveProduct(product);
//            }
            productService.saveProduct(new Product("p1","cola",3,"Cola.jpg",category,16,true));
            productService.saveProduct(new Product("p2","sprite",4,"Sprite.png",category,12,true));
            productService.saveProduct(new Product("p3","red bull",5,"Redbull.jpg",category,4,true));
            productService.saveProduct(new Product("p4","Milk",5,"Milk.jpg",category,1,true));
        };
    }
}
