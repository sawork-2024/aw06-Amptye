package org.example.rest;

import org.example.service.CartService;
import org.example.mapper.ItemMapper;
import org.example.model.Cart;
import org.example.model.Item;
import com.micropos.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.micropos.api.ItemsApi;
import com.micropos.dto.ItemDto;
import com.micropos.dto.ItemFieldsDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class ItemController implements ItemsApi {

    private final CartService cartService;

    private final ItemMapper itemMapper;

    @Autowired
    public ItemController(CartService cartService, ItemMapper itemMapper) {
        this.cartService = cartService;
        this.itemMapper = itemMapper;
    }

    @Override
    public ResponseEntity<List<ItemDto>> listItems() {
        List<ItemDto> items = new ArrayList<>(itemMapper.toItemDtos(this.cartService.findAllItems()));
//        if (items.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> showItemById(Long itemId) {
        Item item = this.cartService.findItemById(itemId);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(itemMapper.toItemDto(item), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addItem(ItemFieldsDto itemFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        ///////////////////////////////////////////////////////
        ProductDto product = cartService.getProductById(itemFieldsDto.getProduct().getId());
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item item = itemMapper.toItem(itemFieldsDto);
        item.setProductId(product.getId());
        item.setProductPrice(product.getPrice());
        item.setProductName(product.getName());
        this.cartService.saveItem(item);
        ItemDto itemDto = itemMapper.toItemDto(item);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/items/{id}")
                .buildAndExpand(item.getId()).toUri());
        return new ResponseEntity<>(itemDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ItemDto> deleteItem(Long itemId) {
        Item item = this.cartService.findItemById(itemId);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.cartService.deleteItem(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ItemDto> updateItem(Long itemId, ItemFieldsDto itemFieldsDto) {
        Item currentItem = this.cartService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(itemFieldsDto.getQuantity());
        this.cartService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addItemById(Long itemId) {
        Item currentItem = this.cartService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(currentItem.getQuantity()+1);
        this.cartService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> subItemById(Long itemId) {
        Item currentItem = this.cartService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(currentItem.getQuantity()-1);
        this.cartService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }
}
