package com.koksao.shop.controllers;

import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.domain.dto.ProductsDto;
import com.koksao.shop.mappers.Mapper;
import com.koksao.shop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    private ProductService productService;
    private Mapper<ProductsEntity, ProductsDto> productsMapper;

    public ProductsController(ProductService productService, Mapper<ProductsEntity, ProductsDto> productsMapper){
        this.productService = productService;
        this.productsMapper = productsMapper;
    }

    @PostMapping(path = "/products")
    public ResponseEntity<ProductsDto> createProduct(@RequestBody ProductsDto productDto){
        ProductsEntity productEntity = productsMapper.mapFrom(productDto);
        ProductsEntity savedProductEntity = productService.createProduct(productEntity);
        return new ResponseEntity<>(productsMapper.mapTo(savedProductEntity), HttpStatus.CREATED);
    }


}
