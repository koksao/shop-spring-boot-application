package com.koksao.shop.controllers;


import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.dto.OrdersRequestDto;
import com.koksao.shop.domain.dto.OrdersResponseDto;
import com.koksao.shop.mappers.Mapper;
import com.koksao.shop.mappers.impl.OrderMapperImpl;
import com.koksao.shop.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    private OrderService orderService;
    private OrderMapperImpl orderMapper;


    public OrderController(OrderService orderService,OrderMapperImpl orderMapper){
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping(path = "/orders")
    public ResponseEntity<OrdersRequestDto> createOrder(@RequestBody OrdersRequestDto ordersRequestDto){
        OrdersEntity savedOrdersEntity = orderService.createOrder(ordersRequestDto);
        return new ResponseEntity<>(orderMapper.mapTo(savedOrdersEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/orders")
    public List<OrdersResponseDto> listOrder(){
        List<OrdersEntity> orders = orderService.findAll();
        return orders.stream()
                .map(orderMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/orders/{orderNumber}")
    public ResponseEntity<OrdersResponseDto> getOrder(@PathVariable("orderNumber") Long orderNumber){
        Optional<OrdersEntity> foundOrder = orderService.findOne(orderNumber);
        return foundOrder.map(ordersEntity -> {
            OrdersResponseDto ordersResponseDto = orderMapper.mapToResponse(ordersEntity);
            return new ResponseEntity<>(ordersResponseDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
