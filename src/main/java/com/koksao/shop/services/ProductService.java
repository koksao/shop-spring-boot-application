package com.koksao.shop.services;

import com.koksao.shop.domain.ProductsEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductsEntity createProduct(ProductsEntity productsEntity);

    List<ProductsEntity> findAll();

    Optional<ProductsEntity> findOne(Long id);
}
