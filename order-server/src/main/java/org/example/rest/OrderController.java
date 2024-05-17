package org.example.rest;

import com.micropos.api.OrdersApi;
import com.micropos.dto.OrderDto;
import com.micropos.dto.OrderDto;
import com.micropos.dto.OrderFieldsDto;
import org.example.mapper.OrderMapper;
import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
public class OrderController implements OrdersApi {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    public ResponseEntity<OrderDto> addOrder(OrderFieldsDto orderFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Order order = orderMapper.toOrder(orderFieldsDto);
        this.orderService.saveOrder(order);
        OrderDto orderDto = orderMapper.toOrderDto(order);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/orders/{id}")
                .buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<>(orderDto, headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<OrderDto>> listOrders() {
        List<OrderDto> orders = new ArrayList<>(orderMapper.toOrderDtos(this.orderService.findAllOrders()));
//        if (orders.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDto> showOrderById(Long orderId) {
        Order order = this.orderService.findOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderMapper.toOrderDto(order), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDto> generateOrder(OrderFieldsDto orderFieldsDto) {
        HttpHeaders headers = new HttpHeaders();
        Order order = orderMapper.toOrder(orderFieldsDto);
        this.orderService.saveOrder(order);
        OrderDto orderDto = orderMapper.toOrderDto(order);
        headers.setLocation(UriComponentsBuilder.newInstance().path("/orders/{id}")
                .buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<>(orderDto, headers, HttpStatus.CREATED);
    }
}
