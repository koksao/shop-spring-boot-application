package com.koksao.shop.services.impl;

import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.repositories.ProductsRepository;
import com.koksao.shop.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    ProductsRepository productsRepository;

    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public ProductsEntity createProduct(ProductsEntity productsEntity) {
        return productsRepository.save(productsEntity);
    }

    @Override
    public List<ProductsEntity> findAll() {
        return StreamSupport.stream(productsRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductsEntity> findOne(Long id) {
        return productsRepository.findById(id);
    }
}
