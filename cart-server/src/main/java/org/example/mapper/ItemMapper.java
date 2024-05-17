package org.example.mapper;

import com.micropos.dto.ProductDto;
import org.example.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.micropos.dto.ItemDto;
import com.micropos.dto.ItemFieldsDto;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    //@Mapping(source = "cart.id", target = "cartId")
    //@Mapping(source = "product.id", target = "productId")
    default ItemDto toItemDto(Item item){
        return new ItemDto().id(item.getId())
                .quantity(item.getQuantity())
                .product(getProductDto(item));
    }

    default Item toItem(ItemDto itemDto){
        Item item = new Item();
        item.setQuantity(itemDto.getQuantity());
        item.setProductId(itemDto.getProduct().getId());
        item.setProductName(itemDto.getProduct().getName());
        item.setProductPrice(itemDto.getProduct().getPrice());
        return item;
    }

    default Item toItem(ItemFieldsDto itemFieldsDto){
        Item item = new Item();
        item.setQuantity(itemFieldsDto.getQuantity());
        item.setProductId(itemFieldsDto.getProduct().getId());
        item.setProductName(itemFieldsDto.getProduct().getName());
        item.setProductPrice(itemFieldsDto.getProduct().getPrice());
        return item;
    }

    List<ItemDto> toItemDtos(Collection<Item> itemCollection);

    Collection<Item> toItems(Collection<ItemDto> itemDtos);

    default ProductDto getProductDto(Item item){
        return new ProductDto().id(item.getProductId())
                .name(item.getProductName())
                .price(item.getProductPrice());
    }
}
