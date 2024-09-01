package com.koksao.shop.mappers.impl;

import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.dto.OrdersDto;
import com.koksao.shop.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements Mapper<OrdersEntity, OrdersDto> {

    ModelMapper modelMapper;

    public OrderMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public OrdersDto mapTo(OrdersEntity ordersEntity) {
        return modelMapper.map(ordersEntity, OrdersDto.class);
    }

    @Override
    public OrdersEntity mapFrom(OrdersDto ordersDto) {
        return modelMapper.map(ordersDto, OrdersEntity.class);
    }
}
