package com.koksao.shop;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.ProductQuantityEntity;
import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.domain.dto.OrdersRequestDto;
import com.koksao.shop.domain.dto.ProductQuantityDto;
import com.koksao.shop.domain.dto.ProductsDto;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {
    private TestDataUtil() {

    }

    public static CustomersEntity createTestCustomerA() {
        return CustomersEntity.builder()
                .id(666L)
                .firstName("Bob")
                .lastName("Budowniczy")
                .phoneNumber("748454985")
                .email("bobbb11@gmail.com")
                .build();
    }

    public static CustomersEntity createTestCustomerB() {
        return CustomersEntity.builder()
                .id(4556L)
                .firstName("Robert")
                .lastName("Makłowicz2")
                .phoneNumber("768465585")
                .email("robertoo22@gmail.com")
                .build();
    }

    public static CustomersEntity createTestCustomerC() {
        return CustomersEntity.builder()
                .id(765432L)
                .firstName("Marcin")
                .lastName("Pudzianowski")
                .phoneNumber("763434099")
                .email("marcin22@gmail.com")
                .build();
    }

    public static ProductsEntity createTestProductA() {
        return ProductsEntity.builder()
                .id(44456L)
                .price(60)
                .color("Pink")
                .clothes(ProductsEntity.Clothes.SHIRT)
                .season(ProductsEntity.Season.WINTER)
                .availability(90)
                .build();
    }

    public static ProductsEntity createTestProductB() {
        return ProductsEntity.builder()
                .id(6544L)
                .price(90)
                .color("Black")
                .clothes(ProductsEntity.Clothes.SHIRT)
                .season(ProductsEntity.Season.WINTER)
                .availability(100)
                .build();
    }

    public static ProductsEntity createTestProductC() {
        return ProductsEntity.builder()
                .id(435461L)
                .price(20)
                .color("Red")
                .clothes(ProductsEntity.Clothes.PANTS)
                .season(ProductsEntity.Season.SUMMER)
                .availability(100)
                .build();
    }

    public static ProductQuantityEntity createTestProductQuantityA(ProductsEntity product) {
        return ProductQuantityEntity.builder()
                .product(product)
                .quantity(15)
                .build();

    }
    public static ProductQuantityEntity createTestProductQuantityB(ProductsEntity product) {
        return ProductQuantityEntity.builder()
                .product(product)
                .quantity(9)
                .build();

    }

    public static OrdersEntity createTestOrderA(CustomersEntity customer){
        return OrdersEntity.builder()
                .customer(customer)
                .build();
    }

    public static OrdersRequestDto createTestOrderDtoA(ProductQuantityDto productQuantityDto1, ProductQuantityDto productQuantityDto2, CustomersEntity customer){
        List<ProductQuantityDto> list = new ArrayList<>();
        list.add(productQuantityDto1);
        list.add(productQuantityDto2);
            return OrdersRequestDto.builder()
                    .customerId(customer.getId())
                    .productsQuantities(list)
                    .build();

    }

    public static ProductQuantityDto createTestProductQuantityDtoA(ProductsEntity product) {
        return ProductQuantityDto.builder()
                .productId(product.getId())
                .quantity(40)
                .build();

    }

    public static ProductQuantityDto createTestProductQuantityDtoB(ProductsEntity product) {
        return ProductQuantityDto.builder()
                .productId(product.getId())
                .quantity(9)
                .build();

    }

    public static ProductsDto createTestProductDtoA(){
        return ProductsDto.builder()
                .id(420L)
                .availability(30)
                .color("WHITE")
                .clothes(ProductsEntity.Clothes.PANTS)
                .season(ProductsEntity.Season.WINTER)
                .price(90)
                .build();
    }

    public static ProductsDto createTestProductDtoB(){
        return ProductsDto.builder()
                .id(320L)
                .availability(60)
                .color("WHITE")
                .clothes(ProductsEntity.Clothes.SHIRT)
                .season(ProductsEntity.Season.WINTER)
                .price(40)
                .build();
    }




}
