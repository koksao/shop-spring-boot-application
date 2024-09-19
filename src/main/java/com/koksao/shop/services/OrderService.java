package com.koksao.shop.services;

import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.dto.OrdersRequestDto;

import java.util.List;

public interface OrderService {

    OrdersEntity createOrder (OrdersRequestDto ordersRequestDto);

    List<OrdersEntity> findAll();
}
