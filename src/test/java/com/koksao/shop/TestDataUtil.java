package com.koksao.shop;

import com.koksao.shop.domain.Customers;
import com.koksao.shop.domain.Orders;
import com.koksao.shop.domain.ProductQuantity;
import com.koksao.shop.domain.Products;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {
    private TestDataUtil() {

    }

    public static Customers createTestCustomerA() {
        return Customers.builder()
                .firstName("Robert")
                .lastName("Makłowicz")
                .phoneNumber(768464085)
                .email("robertoo11@gmail.com")
                .build();
    }

    public static Customers createTestCustomerB() {
        return Customers.builder()
                .firstName("Robert")
                .lastName("Makłowicz2")
                .phoneNumber(768465585)
                .email("robertoo22@gmail.com")
                .build();
    }

    public static Customers createTestCustomerC() {
        return Customers.builder()
                .firstName("Marcin")
                .lastName("Pudzianowski")
                .phoneNumber(763434099)
                .email("marcin22@gmail.com")
                .build();
    }

    public static Products createTestProductA() {
        return Products.builder()
                .price(60)
                .color("Pink")
                .clothes(Products.Clothes.SHIRT)
                .season(Products.Season.WINTER)
                .availability(90)
                .build();
    }

    public static Products createTestProductB() {
        return Products.builder()
                .price(90)
                .color("Black")
                .clothes(Products.Clothes.SHIRT)
                .season(Products.Season.WINTER)
                .availability(100)
                .build();
    }

    public static Products createTestProductC() {
        return Products.builder()
                .price(20)
                .color("Red")
                .clothes(Products.Clothes.PANTS)
                .season(Products.Season.SUMMER)
                .availability(100)
                .build();
    }

    public static ProductQuantity createTestProductQuantityA(Products product) {
        return ProductQuantity.builder()
                .product(product)
                .quantity(15)
                .build();

    }
    public static ProductQuantity createTestProductQuantityB(Products product) {
        return ProductQuantity.builder()
                .product(product)
                .quantity(9)
                .build();

    }

    public static Orders createTestOrderA(Customers customer){
        return Orders.builder()
                .customer(customer)
                .totalPrice(90.0)
                .build();
    }




}
