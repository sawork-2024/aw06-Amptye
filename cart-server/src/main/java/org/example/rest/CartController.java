package org.example.rest;

import com.micropos.dto.*;
import org.example.service.CartService;
import org.example.mapper.CartMapper;
import org.example.mapper.ItemMapper;
import org.example.model.Cart;
import org.example.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.micropos.api.CartsApi;
import com.micropos.api.ItemsApi;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class CartController implements CartsApi {

    private final CartService cartService;

    private final CartMapper cartMapper;
    private final ItemMapper itemMapper;

    @Autowired
    public CartController(CartService cartService, CartMapper cartMapper, ItemMapper itemMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public ResponseEntity<List<CartDto>> listCarts() {
        List<CartDto> carts = new ArrayList<>(cartMapper.toCartDtos(this.cartService.findAllCarts()));
        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> showCartById(Long cartId) {
        Cart cart = this.cartService.findCartById(cartId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartMapper.toCartDto(cart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addItemToCart(Long cartId, ItemFieldsDto itemFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Cart cart = cartService.findCartById(cartId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ///////////////////////////////////////
        ProductDto product = cartService.getProductById(itemFieldsDto.getProduct().getId());
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item item = itemMapper.toItem(itemFieldsDto);
        item.setProductId(product.getId());
        item.setProductPrice(product.getPrice());
        item.setProductName(product.getName());
        item.setCart(cart);
        this.cartService.saveItem(item);
        cart.addItem(item);
        this.cartService.saveItem(item);
        ItemDto itemDto = itemMapper.toItemDto(item);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/carts/{cartId}")
                .buildAndExpand(cartId).toUri());
        return new ResponseEntity<>(itemDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ItemDto> getCartsItem(Long cartId, Long itemId) {
        Cart cart = this.cartService.findCartById(cartId);
        Item item = this.cartService.findItemById(itemId);
        if (cart == null || item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!item.getCart().equals(cart)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(itemMapper.toItemDto(item), HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<List<ItemDto>> listCartsItems(Long cartId) {
        Cart cart = this.cartService.findCartById(cartId);
        if (cart == null || cart.getItems().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<ItemDto> items = itemMapper.toItemDtos(cart.getItems());
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Void> updateCartsItem(Long cartId, Long itemId, ItemFieldsDto itemFieldsDto) {
        Cart cart = this.cartService.findCartById(cartId);
        Item item = this.cartService.findItemById(itemId);
        if (cart == null || item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!item.getCart().equals(cart)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                item.setQuantity(itemFieldsDto.getQuantity());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @Override
    public ResponseEntity<CartDto> addCart(CartFieldsDto cartFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Cart cart = cartMapper.toCart(cartFieldsDto);
        this.cartService.saveCart(cart);
        CartDto cartDto = cartMapper.toCartDto(cart);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/carts/{id}")
                .buildAndExpand(cart.getId()).toUri());
        return new ResponseEntity<>(cartDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CartDto> deleteCart(Long cartId) {
        Cart cart = this.cartService.findCartById(cartId);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.cartService.deleteCart(cart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ItemDto> deleteCartsItem(Long cartId, Long itemId) {
        Cart cart = this.cartService.findCartById(cartId);
        Item item = this.cartService.findItemById(itemId);
        if (cart == null || item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!item.getCart().equals(cart)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                this.cartService.deleteItem(item);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @Override
    public ResponseEntity<CartDto> clearCart(Long cartId) {
        Cart currentCart = this.cartService.findCartById(cartId);
        if (currentCart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentCart.getItems().clear();
        this.cartService.saveCart(currentCart);
        return new ResponseEntity<>(cartMapper.toCartDto(currentCart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> addCartsItemById(Long cartId, Long itemId) {
        Cart currentCart = this.cartService.findCartById(cartId);
        if (currentCart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item currentItem = this.cartService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(currentItem.getQuantity()+1);
        this.cartService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDto> subCartsItemById(Long cartId, Long itemId) {
        Cart currentCart = this.cartService.findCartById(cartId);
        if (currentCart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item currentItem = this.cartService.findItemById(itemId);
        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentItem.setQuantity(currentItem.getQuantity()-1);
        this.cartService.saveItem(currentItem);
        return new ResponseEntity<>(itemMapper.toItemDto(currentItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> addProductToCart(Long cartId, Long productId) {
        Cart currentCart = this.cartService.findCartById(cartId);
        if (currentCart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductDto product = this.cartService.getProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item old_item = null;
        for(Item item : currentCart.getItems()){
            if(item.getProductId() == productId){
                old_item = item;
                break;
            }
        }
        if(old_item == null){
            Item item = new Item(currentCart, product.getId(), 1);
            this.cartService.saveItem(item);
            currentCart.addItem(item);
            this.cartService.saveItem(item);
        } else {
            old_item.setQuantity(old_item.getQuantity()+1);
            this.cartService.saveItem(old_item);
        }
        return new ResponseEntity<>(cartMapper.toCartDto(currentCart), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ChargeCartById200ResponseDto> chargeCartById(Long cartId, UserDto userDto) {
        if(!Objects.equals(cartId, userDto.getCartId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        double money = userDto.getMoney();
        boolean tax = userDto.getTax();
        double percentage = userDto.getPercentage();
        double total = 0;
        Cart cart = cartService.findCartById(cartId);
        if(cart == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for(Item item : cart.getItems()){
            if(tax){
                total += item.getQuantity() * item.getProductPrice() * (1+percentage);
            } else {
                total += item.getQuantity() * item.getProductPrice();
            }
        }
        if(total <= money) {
            cartService.generateOrder(cart, userDto);
            Cart new_cart = new Cart();
            return new ResponseEntity<>(
                    new ChargeCartById200ResponseDto().total(total).cartId(new_cart.getId()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
