package org.example.mapper;

import com.micropos.dto.TaxFieldsDto;
import com.micropos.dto.UserDto;
import com.micropos.dto.UserFieldsDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPass( user.getPass() );
        userDto.setMoney( user.getMoney() );
        userDto.setAddress1( user.getAddress1() );
        userDto.setAddress2( user.getAddress2() );
        userDto.setContact( user.getContact() );
        userDto.setSymbol( user.getSymbol() );
        userDto.setFooter( user.getFooter() );
        userDto.setImage( user.getImage() );
        userDto.setTax( user.isTax() );
        userDto.setPercentage( user.getPercentage() );
        userDto.setId( user.getId() );
        userDto.setCartId( user.getCartId() );

        return userDto;
    }

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        if ( userDto.getId() != null ) {
            user.setId( userDto.getId() );
        }
        user.setName( userDto.getName() );
        user.setEmail( userDto.getEmail() );
        user.setPass( userDto.getPass() );
        if ( userDto.getMoney() != null ) {
            user.setMoney( userDto.getMoney() );
        }
        if ( userDto.getCartId() != null ) {
            user.setCartId( userDto.getCartId() );
        }
        user.setAddress1( userDto.getAddress1() );
        user.setAddress2( userDto.getAddress2() );
        user.setContact( userDto.getContact() );
        if ( userDto.getTax() != null ) {
            user.setTax( userDto.getTax() );
        }
        if ( userDto.getPercentage() != null ) {
            user.setPercentage( userDto.getPercentage() );
        }
        user.setSymbol( userDto.getSymbol() );
        user.setFooter( userDto.getFooter() );
        user.setImage( userDto.getImage() );

        return user;
    }

    @Override
    public User toUser(UserFieldsDto userFieldsDto) {
        if ( userFieldsDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( userFieldsDto.getName() );
        user.setEmail( userFieldsDto.getEmail() );
        user.setPass( userFieldsDto.getPass() );
        if ( userFieldsDto.getMoney() != null ) {
            user.setMoney( userFieldsDto.getMoney() );
        }
        user.setAddress1( userFieldsDto.getAddress1() );
        user.setAddress2( userFieldsDto.getAddress2() );
        user.setContact( userFieldsDto.getContact() );
        user.setSymbol( userFieldsDto.getSymbol() );
        user.setFooter( userFieldsDto.getFooter() );
        user.setImage( userFieldsDto.getImage() );

        return user;
    }

    @Override
    public User toUser(TaxFieldsDto taxFieldsDto) {
        if ( taxFieldsDto == null ) {
            return null;
        }

        User user = new User();

        if ( taxFieldsDto.getTax() != null ) {
            user.setTax( taxFieldsDto.getTax() );
        }
        if ( taxFieldsDto.getPercentage() != null ) {
            user.setPercentage( taxFieldsDto.getPercentage() );
        }

        return user;
    }

    @Override
    public List<UserDto> toUserDtos(Collection<User> userCollection) {
        if ( userCollection == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userCollection.size() );
        for ( User user : userCollection ) {
            list.add( toUserDto( user ) );
        }

        return list;
    }

    @Override
    public Collection<User> toUsers(Collection<UserDto> userDtos) {
        if ( userDtos == null ) {
            return null;
        }

        Collection<User> collection = new ArrayList<User>( userDtos.size() );
        for ( UserDto userDto : userDtos ) {
            collection.add( toUser( userDto ) );
        }

        return collection;
    }
}
