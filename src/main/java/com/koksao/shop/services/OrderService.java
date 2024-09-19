package com.koksao.shop.services;

import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.dto.OrdersRequestDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrdersEntity createOrder (OrdersRequestDto ordersRequestDto);

    List<OrdersEntity> findAll();

    Optional<OrdersEntity> findOne(Long orderNumber);
}
