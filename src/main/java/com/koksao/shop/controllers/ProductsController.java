package com.koksao.shop.controllers;

import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.domain.dto.ProductsDto;
import com.koksao.shop.mappers.Mapper;
import com.koksao.shop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/products")
    public List<ProductsDto> listProducts(){
        List<ProductsEntity> products = productService.findAll();
        return products.stream()
                .map(productsMapper::mapTo)
                .collect(Collectors.toList());
    }



}
