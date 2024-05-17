package org.example.rest;

import com.micropos.dto.*;
import org.example.service.UserService;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.micropos.api.UsersApi;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class UserController implements UsersApi {

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<List<UserDto>> listUsers() {
        List<UserDto> users = new ArrayList<>(userMapper.toUserDtos(this.userService.findAllUsers()));
//        if (users.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> showUserById(Long userId) {
        User user = this.userService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> addUser(UserFieldsDto userFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        User user = userMapper.toUser(userFieldsDto);
        user.setTax(false);
        user.setPercentage(0);
        this.userService.saveUser(user);
        UserDto userDto = userMapper.toUserDto(user);
        headers.setLocation(UriComponentsBuilder.newInstance()
                .path("/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(userDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long userId, UserFieldsDto userFieldsDto) {
        User currentUser = this.userService.findUserById(userId);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.setName(userFieldsDto.getName());
        if(userFieldsDto.getPass() != null) {
            currentUser.setPass(userFieldsDto.getPass());
        }
        if(userFieldsDto.getEmail() != null) {
            currentUser.setEmail(userFieldsDto.getEmail());
        }
        if(userFieldsDto.getMoney() != null) {
            currentUser.setMoney(userFieldsDto.getMoney());
        }
        if(userFieldsDto.getAddress1() != null){
            currentUser.setAddress1(userFieldsDto.getAddress1());
        }
        if(userFieldsDto.getAddress2() != null){
            currentUser.setAddress2(userFieldsDto.getAddress2());
        }
        if(userFieldsDto.getContact() != null){
            currentUser.setContact(userFieldsDto.getContact());
        }
        if(userFieldsDto.getFooter() != null){
            currentUser.setFooter(userFieldsDto.getFooter());
        }
        if(userFieldsDto.getImage() != null){
            currentUser.setFooter(userFieldsDto.getFooter());
        }
        if(userFieldsDto.getSymbol() != null){
            currentUser.setSymbol(userFieldsDto.getSymbol());
        }
        this.userService.saveUser(currentUser);
        return new ResponseEntity<>(userMapper.toUserDto(currentUser), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> deleteUser(Long userId) {
        User user = this.userService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<UserDto> chargeUserById(Long userId) {
        User user = this.userService.findUserById(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ChargeCartById200ResponseDto result = userService.charge(userId);
        double total = result.getTotal();
        if(total >= 0) {
            user.subMoney(total);
            user.setCartId(result.getCartId());
            userService.saveUser(user);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(user);
        return new ResponseEntity<>(userMapper.toUserDto(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> taxUpdateUser(Long userId, TaxFieldsDto taxFieldsDto) {
        User currentUser = this.userService.findUserById(userId);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.setTax(taxFieldsDto.getTax());
        if(taxFieldsDto.getPercentage() != null){
            currentUser.setPercentage(taxFieldsDto.getPercentage());
        }
        this.userService.saveUser(currentUser);
        return new ResponseEntity<>(userMapper.toUserDto(currentUser), HttpStatus.OK);
    }
}
