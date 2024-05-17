package org.example.service;

import org.example.model.Order;

import java.util.Collection;
import java.util.List;

public interface OrderService {
    public Order findOrderById(long orderId);
    public Collection<Order> findAllOrders();
    public void deleteOrder(Order order);
    public List<Order> findOrdersByUserId(long userId);
    public List<Order> findOrdersByCartId(long cartId);
    public void saveOrder(Order order);
}
