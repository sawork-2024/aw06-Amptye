package org.example.service;

import com.micropos.dto.ChargeCartById200ResponseDto;
import org.example.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    public ChargeCartById200ResponseDto charge(long userId);
    public ChargeCartById200ResponseDto charge(User user);

    public Collection<User> findAllUsers();
    public User findUserById(long id);
    public void deleteUser(User user);
    public void saveUser(User user);
    public List<User> findUsersByName(String Name);
}
