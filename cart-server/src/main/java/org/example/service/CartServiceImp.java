package org.example.service;

import com.micropos.dto.*;
import org.example.mapper.CartMapper;
import org.example.mapper.ItemMapper;
import org.example.model.Cart;
import org.example.model.Item;
import org.example.repository.CartRepository;
import org.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Service
@Component
public class CartServiceImp implements CartService {
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    private final String CART_URL = "http://cart-server/";
    private final String PRODUCT_URL = "http://product-server/";
    private final String ORDER_URL = "http://order-server/";

    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    public CartServiceImp(CartMapper cartMapper,
                          CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartMapper = cartMapper;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductDto getProductById(long productId) {
        try {
            return restTemplate.getForObject(PRODUCT_URL+ "products/" + productId, ProductDto.class);
        } catch (Exception e){// HttpClientErrorException.NotFound
            return null;
        }
    }

    @Override
    public boolean generateOrder(Cart cart, UserDto userDto) {
        CartDto cartDto = cartMapper.toCartDto(cart);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        OrderDto result;
        OrderFieldsDto orderFieldsDto = new OrderFieldsDto()
                .user(userDto)
                .cart(cartDto);
        try {
             result = restTemplate.postForObject(ORDER_URL+ "orders/generate",
                    new HttpEntity<>(orderFieldsDto, headers), OrderDto.class);
        } catch (Exception e){
            result = null;
        }
        return result != null;
    }

    @Override
    @Cacheable(value = "items", key = "#id")
    //@Transactional(readOnly = true)
    public Item findItemById(long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }
    @Override
    @Cacheable(value = "items")
    //@Transactional(readOnly = true)
    public Collection<Item> findAllItems() {
        return itemRepository.findAll();
    }
    @Override
    @CacheEvict(value = "items", allEntries = true)
    //@Transactional
    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }
    @Override
    @Cacheable(value = "items", key = "'productId-' + #productId")
    //@Transactional(readOnly = true)
    public List<Item> findItemsByProductId(long productId) {
        return itemRepository.findByProductId(productId);
    }
    @Override
    @CacheEvict(value = "items", allEntries = true)
    //@Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    @Cacheable(value = "carts", key = "#id")
    //@Transactional(readOnly = true)
    public Cart findCartById(long id) {
        return cartRepository.findById(id).orElse(null);
    }
    @Override
    @Cacheable(value = "carts")
    //@Transactional(readOnly = true)
    public Collection<Cart> findAllCarts() {
        return cartRepository.findAll();
    }
    @Override
    @CacheEvict(value = "carts", allEntries = true)
    //@Transactional
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }
    @Override
    @CacheEvict(value = "carts", allEntries = true)
    //@Transactional
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }
}
