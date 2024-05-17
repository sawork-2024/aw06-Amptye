package org.example.mapper;

import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.micropos.dto.TaxFieldsDto;
import com.micropos.dto.UserDto;
import com.micropos.dto.UserFieldsDto;

import java.util.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(source = "cart.id", target = "cartId")
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    User toUser(UserFieldsDto userFieldsDto);
    User toUser(TaxFieldsDto taxFieldsDto);

    List<UserDto> toUserDtos(Collection<User> userCollection);

    Collection<User> toUsers(Collection<UserDto> userDtos);
}
