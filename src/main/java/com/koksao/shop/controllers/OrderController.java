package com.koksao.shop.controllers;


import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.dto.OrdersDto;
import com.koksao.shop.mappers.Mapper;
import com.koksao.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private OrderService orderService;
    private Mapper<OrdersEntity, OrdersDto> orderMapper;


    public OrderController(OrderService orderService, Mapper<OrdersEntity, OrdersDto> orderMapper){
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping(path = "/orders")
    public ResponseEntity<OrdersDto> createOrder(@RequestBody OrdersDto ordersDto){
        OrdersEntity savedOrdersEntity = orderService.createOrder(ordersDto);
        return new ResponseEntity<>(orderMapper.mapTo(savedOrdersEntity), HttpStatus.CREATED);
    }

}
