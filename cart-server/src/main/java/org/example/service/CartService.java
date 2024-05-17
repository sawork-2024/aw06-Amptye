package org.example.service;

import com.micropos.dto.CartDto;
import com.micropos.dto.ProductDto;
import com.micropos.dto.UserDto;
import org.example.model.Cart;
import org.example.model.Item;

import java.util.Collection;
import java.util.List;

public interface CartService {
    public ProductDto getProductById(long productId);
    public boolean generateOrder(Cart cart, UserDto userDto);

    public Cart findCartById(long id);
    public Collection<Cart> findAllCarts();
    public void saveCart(Cart cart);
    public void deleteCart(Cart cart);

    public Item findItemById(long itemId);
    public Collection<Item> findAllItems();
    public void deleteItem(Item item);
    public List<Item> findItemsByProductId(long productId);
    public void saveItem(Item item);
}
