package org.example.mapper;

import org.example.model.Cart;
import org.mapstruct.Mapper;
import com.micropos.dto.CartDto;
import com.micropos.dto.CartFieldsDto;

import java.util.Collection;
import java.util.*;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface CartMapper {

    //@Mapping(source = "user.id", target = "userId")
    CartDto toCartDto(Cart cart);

    Cart toCart(CartDto cartDto);

    Cart toCart(CartFieldsDto cartFieldsDto);

    List<CartDto> toCartDtos(Collection<Cart> cartCollection);

    Collection<Cart> toCarts(Collection<CartDto> cartDtos);
}
