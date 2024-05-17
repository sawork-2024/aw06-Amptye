package org.example.service;

import org.example.model.Order;
import org.example.mapper.OrderMapper;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Service
@Component
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;

    private final String ORDER_URL = "http://order-server/";

    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = "orders", key = "#id")
    //@Transactional(readOnly = true)
    public Order findOrderById(long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
    @Override
    @Cacheable(value = "orders")
    //@Transactional(readOnly = true)
    public Collection<Order> findAllOrders() {
        return orderRepository.findAll();
    }
    @Override
    @CacheEvict(value = "orders", allEntries = true)
    //@Transactional
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
    @Override
    @Cacheable(value = "orders", key = "'userId-' + #userId")
    //@Transactional(readOnly = true)
    public List<Order> findOrdersByUserId(long userId) {
        return orderRepository.findByUserId(userId);
    }
    @Override
    @Cacheable(value = "orders", key = "'cartId-' + #cartId")
    //@Transactional(readOnly = true)
    public List<Order> findOrdersByCartId(long cartId) {
        return orderRepository.findByUserId(cartId);
    }
    @Override
    @CacheEvict(value = "orders", allEntries = true)
    //@Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

}
