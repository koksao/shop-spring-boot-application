package com.koksao.shop.controllers;

import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.domain.dto.ProductsDto;
import com.koksao.shop.mappers.Mapper;
import com.koksao.shop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductsDto> getProduct(@PathVariable("id") Long id){
        Optional<ProductsEntity> foundProduct = productService.findOne(id);
        return foundProduct.map(productsEntity -> {
            ProductsDto productsDto = productsMapper.mapTo(productsEntity);
            return new ResponseEntity<>(productsDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/products/{id}")
    public ResponseEntity<ProductsDto> fullUpdateProduct
            (@PathVariable("id") Long id, @RequestBody ProductsDto productsDto){
        if(!productService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productsDto.setId(id);
        ProductsEntity productsEntity = productsMapper.mapFrom(productsDto);
        ProductsEntity savedProductsEntity = productService.createProduct(productsEntity);
        return new ResponseEntity<>(
                productsMapper.mapTo(savedProductsEntity),
                HttpStatus.OK);

    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id){
        productService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
