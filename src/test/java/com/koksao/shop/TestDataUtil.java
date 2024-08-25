package com.koksao.shop;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.ProductQuantityEntity;
import com.koksao.shop.domain.ProductsEntity;

public class TestDataUtil {
    private TestDataUtil() {

    }

    public static CustomersEntity createTestCustomerA() {
        return CustomersEntity.builder()
                .firstName("Robert")
                .lastName("Makłowicz")
                .phoneNumber("768464085")
                .email("robertoo11@gmail.com")
                .build();
    }

    public static CustomersEntity createTestCustomerB() {
        return CustomersEntity.builder()
                .firstName("Robert")
                .lastName("Makłowicz2")
                .phoneNumber("768465585")
                .email("robertoo22@gmail.com")
                .build();
    }

    public static CustomersEntity createTestCustomerC() {
        return CustomersEntity.builder()
                .firstName("Marcin")
                .lastName("Pudzianowski")
                .phoneNumber("763434099")
                .email("marcin22@gmail.com")
                .build();
    }

    public static ProductsEntity createTestProductA() {
        return ProductsEntity.builder()
                .price(60)
                .color("Pink")
                .clothes(ProductsEntity.Clothes.SHIRT)
                .season(ProductsEntity.Season.WINTER)
                .availability(90)
                .build();
    }

    public static ProductsEntity createTestProductB() {
        return ProductsEntity.builder()
                .price(90)
                .color("Black")
                .clothes(ProductsEntity.Clothes.SHIRT)
                .season(ProductsEntity.Season.WINTER)
                .availability(100)
                .build();
    }

    public static ProductsEntity createTestProductC() {
        return ProductsEntity.builder()
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
                .totalPrice(90.0)
                .build();
    }




}
