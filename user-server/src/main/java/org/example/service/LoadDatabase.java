package org.example.service;

import org.example.model.User;
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

    private final UserService userService;

    @Autowired
    public LoadDatabase(UserService userService){
        this.userService = userService;
    }

    @Bean
    CommandLineRunner initDatabase() {

        return args -> {
            userService.saveUser(new User("123","10086","10087","15968774896",true,10,"$","",""));
        };
    }
}
