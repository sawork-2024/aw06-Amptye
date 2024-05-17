package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micropos.dto.ChargeCartById200ResponseDto;
import com.micropos.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Service
@Component
public class UserServiceImp implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final String USER_URL = "http://user-server/";
    private final String CART_URL = "http://cart-server/";

    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    public UserServiceImp(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ChargeCartById200ResponseDto charge(long userId) {
        User user = this.findUserById(userId);
        if(user == null){
            return null;
        }
        return this.charge(user);
    }
    @Override
    public ChargeCartById200ResponseDto charge(User user) {
        UserDto userDto = userMapper.toUserDto(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            return restTemplate.postForObject(CART_URL+ "carts/" + user.getCartId() + "/charge",
                    new HttpEntity<>(userDto, headers), ChargeCartById200ResponseDto.class);
        } catch (Exception e){// HttpClientErrorException.BadRequest
            return new ChargeCartById200ResponseDto().total(-1d);
        }
    }

    @Override
    @Cacheable(value = "users")
    //@Transactional(readOnly = true)
    public Collection<User> findAllUsers() {
        return userRepository.findAll();
    }
    @Override
    @Cacheable(value = "users", key = "#id")
    //@Transactional(readOnly = true)
    public User findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    @CacheEvict(value = "users", allEntries = true)
    //@Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    @Override
    @CacheEvict(value = "users", allEntries = true)
    //@Transactional
    public void saveUser(User user) {
        userRepository.save(user);
//        CartDto cart = new CartDto();
//        cart.setUser(user);
//        this.saveCart(cart);
//        user.setCart(cart);
//        userRepository.save(user);
    }
    @Override
    @Cacheable(value = "users", key = "'name-' + #name")
    //@Transactional(readOnly = true)
    public List<User> findUsersByName(String Name) {
        return userRepository.findByName(Name);
    }
}
