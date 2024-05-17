package org.example.mapper;

import com.micropos.dto.CartDto;
import com.micropos.dto.OrderDto;
import com.micropos.dto.OrderFieldsDto;
import com.micropos.dto.UserDto;
import org.aspectj.weaver.ast.Or;
import org.example.model.Order;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default OrderDto toOrderDto(Order order){
        return new OrderDto().id(order.getId())
                .cart(new CartDto().id(order.getCartId()))
                .user(new UserDto().id(order.getUserId()));
    }

    default Order toOrder(OrderDto orderDto){
        Order order = new Order();
        order.setCartId(orderDto.getCart().getId());
        order.setUserId(orderDto.getUser().getId());
        return order;
    }

    default Order toOrder(OrderFieldsDto ordersFieldsDto){
        Order order = new Order();
        order.setCartId(ordersFieldsDto.getCart().getId());
        order.setUserId(ordersFieldsDto.getUser().getId());
        return order;
    }

    List<OrderDto> toOrderDtos(Collection<Order> orderCollection);

    Collection<Order> toOrders(Collection<OrderDto> orderDtos);
}
