package com.koksao.shop.services;

import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.dto.OrdersDto;

public interface OrderService {

    OrdersEntity createOrder (OrdersDto ordersDto);
}
