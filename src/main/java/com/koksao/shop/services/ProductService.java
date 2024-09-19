package com.koksao.shop.services;

import com.koksao.shop.domain.ProductsEntity;

import java.util.List;

public interface ProductService {

    ProductsEntity createProduct(ProductsEntity productsEntity);

    List<ProductsEntity> findAll();
}
