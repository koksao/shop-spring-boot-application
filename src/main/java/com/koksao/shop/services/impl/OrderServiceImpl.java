package com.koksao.shop.services.impl;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.ProductQuantityEntity;
import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.domain.dto.OrdersDto;
import com.koksao.shop.domain.dto.ProductQuantityDto;
import com.koksao.shop.repositories.CustomersRepository;
import com.koksao.shop.repositories.OrdersRepository;
import com.koksao.shop.repositories.ProductQuantityRepository;
import com.koksao.shop.repositories.ProductsRepository;
import com.koksao.shop.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    OrdersRepository ordersRepository;
    ProductQuantityRepository productQuantityRepository;
    CustomersRepository customersRepository;
    ProductsRepository productsRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository, ProductQuantityRepository productQuantityRepository,
                            CustomersRepository customersRepository, ProductsRepository productsRepository){
        this.ordersRepository = ordersRepository;
        this.customersRepository = customersRepository;
        this.productsRepository = productsRepository;
        this.productQuantityRepository = productQuantityRepository;
    }


    @Override
    public OrdersEntity createOrder(OrdersDto ordersDto) {
        CustomersEntity customer = customersRepository.findById(ordersDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID " + ordersDto.getCustomerId()));

        OrdersEntity order = new OrdersEntity();
        order.setCustomer(customer);

        OrdersEntity savedOrder = ordersRepository.save(order);

        for (ProductQuantityDto prqDto : ordersDto.getProductsQuantities()){
            ProductsEntity product = productsRepository.findById(prqDto.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + prqDto.getProductId()));

            ProductQuantityEntity productQuantity = new ProductQuantityEntity();
            productQuantity.setOrder(savedOrder);
            productQuantity.setProduct(product);
            productQuantity.setQuantity(prqDto.getQuantity());

            productQuantityRepository.save(productQuantity);
        }

        return savedOrder;
    }
}
