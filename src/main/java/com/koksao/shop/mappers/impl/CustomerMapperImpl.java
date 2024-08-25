package com.koksao.shop.mappers.impl;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.dto.CustomersDto;
import com.koksao.shop.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements Mapper<CustomersEntity, CustomersDto> {

    private ModelMapper modelMapper;

    public CustomerMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomersDto mapTo(CustomersEntity customersEntity) {
        return modelMapper.map(customersEntity, CustomersDto.class);
    }

    @Override
    public CustomersEntity mapFrom(CustomersDto customersDto) {
        return modelMapper.map(customersDto, CustomersEntity.class);
    }
}
