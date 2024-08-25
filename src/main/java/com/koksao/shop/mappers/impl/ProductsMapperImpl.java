package com.koksao.shop.mappers.impl;

import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.domain.dto.ProductsDto;
import com.koksao.shop.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;

@Component
public class ProductsMapperImpl implements Mapper<ProductsEntity, ProductsDto> {

    private ModelMapper modelMapper;

    public ProductsMapperImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductsDto mapTo(ProductsEntity productsEntity) {
        return modelMapper.map(productsEntity, ProductsDto.class);
    }

    @Override
    public ProductsEntity mapFrom(ProductsDto productsDto) {
        return modelMapper.map(productsDto, ProductsEntity.class);
    }
}
