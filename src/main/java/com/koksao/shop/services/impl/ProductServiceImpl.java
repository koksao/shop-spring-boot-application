package com.koksao.shop.services.impl;

import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.repositories.ProductsRepository;
import com.koksao.shop.services.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    ProductsRepository productsRepository;

    public ProductServiceImpl(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    @Override
    public ProductsEntity createProduct(ProductsEntity productsEntity) {
        return productsRepository.save(productsEntity);
    }
}
