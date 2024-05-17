package org.example.mapper;

import com.micropos.dto.CartDto;
import com.micropos.dto.CartFieldsDto;
import com.micropos.dto.ItemDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.model.Cart;
import org.example.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public CartDto toCartDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setItems( itemMapper.toItemDtos( cart.getItems() ) );
        cartDto.setId( cart.getId() );

        return cartDto;
    }

    @Override
    public Cart toCart(CartDto cartDto) {
        if ( cartDto == null ) {
            return null;
        }

        Cart cart = new Cart();

        if ( cartDto.getId() != null ) {
            cart.setId( cartDto.getId() );
        }
        cart.setItems( itemDtoListToItemList( cartDto.getItems() ) );

        return cart;
    }

    @Override
    public Cart toCart(CartFieldsDto cartFieldsDto) {
        if ( cartFieldsDto == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setItems( itemDtoListToItemList( cartFieldsDto.getItems() ) );

        return cart;
    }

    @Override
    public List<CartDto> toCartDtos(Collection<Cart> cartCollection) {
        if ( cartCollection == null ) {
            return null;
        }

        List<CartDto> list = new ArrayList<CartDto>( cartCollection.size() );
        for ( Cart cart : cartCollection ) {
            list.add( toCartDto( cart ) );
        }

        return list;
    }

    @Override
    public Collection<Cart> toCarts(Collection<CartDto> cartDtos) {
        if ( cartDtos == null ) {
            return null;
        }

        Collection<Cart> collection = new ArrayList<Cart>( cartDtos.size() );
        for ( CartDto cartDto : cartDtos ) {
            collection.add( toCart( cartDto ) );
        }

        return collection;
    }

    protected List<Item> itemDtoListToItemList(List<ItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Item> list1 = new ArrayList<Item>( list.size() );
        for ( ItemDto itemDto : list ) {
            list1.add( itemMapper.toItem( itemDto ) );
        }

        return list1;
    }
}
