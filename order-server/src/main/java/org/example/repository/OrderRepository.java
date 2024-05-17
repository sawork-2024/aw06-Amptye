package org.example.repository;

import org.example.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(long userId);
    List<Order> findByCartId(long cartId);
}
