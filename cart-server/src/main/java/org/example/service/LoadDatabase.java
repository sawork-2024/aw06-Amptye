package org.example.service;

import org.example.model.Cart;
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

    private final CartService cartService;

    @Autowired
    public LoadDatabase(CartService cartService){
        this.cartService = cartService;
    }

    @Bean
    CommandLineRunner initDatabase() {

        return args -> {
            cartService.saveCart(new Cart());
        };
    }
}
