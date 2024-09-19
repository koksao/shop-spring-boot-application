package com.koksao.shop.mappers.impl;

import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.dto.OrdersRequestDto;
import com.koksao.shop.domain.dto.OrdersResponseDto;
import com.koksao.shop.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements Mapper<OrdersEntity, OrdersRequestDto> {

    ModelMapper modelMapper;

    public OrderMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;

        modelMapper.addMappings(new PropertyMap<OrdersEntity, OrdersResponseDto>() {
            @Override
            protected void configure() {
                map().setCustomerId(source.getCustomer().getId());
                map().setCustomerFirstName(source.getCustomer().getFirstName());
                map().setCustomerLastName(source.getCustomer().getLastName());
            }
        });
    }

    @Override
    public OrdersRequestDto mapTo(OrdersEntity ordersEntity) {
        return modelMapper.map(ordersEntity, OrdersRequestDto.class);
    }

    @Override
    public OrdersEntity mapFrom(OrdersRequestDto ordersRequestDto) {
        return modelMapper.map(ordersRequestDto, OrdersEntity.class);
    }

    public OrdersResponseDto mapToResponse(OrdersEntity ordersEntity){
        return modelMapper.map(ordersEntity, OrdersResponseDto.class);
    }
}
