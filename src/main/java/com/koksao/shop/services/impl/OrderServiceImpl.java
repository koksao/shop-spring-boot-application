package com.koksao.shop.services.impl;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.ProductQuantityEntity;
import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.domain.dto.OrdersRequestDto;
import com.koksao.shop.domain.dto.ProductQuantityDto;
import com.koksao.shop.repositories.CustomersRepository;
import com.koksao.shop.repositories.OrdersRepository;
import com.koksao.shop.repositories.ProductQuantityRepository;
import com.koksao.shop.repositories.ProductsRepository;
import com.koksao.shop.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public OrdersEntity createOrder(OrdersRequestDto ordersRequestDto) {
        CustomersEntity customer = customersRepository.findById(ordersRequestDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID " + ordersRequestDto.getCustomerId()));

        OrdersEntity order = new OrdersEntity();
        order.setCustomer(customer);

        OrdersEntity savedOrder = ordersRepository.save(order);

        for (ProductQuantityDto prqDto : ordersRequestDto.getProductsQuantities()){
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

    @Override
    public List<OrdersEntity> findAll() {
        return StreamSupport.stream(ordersRepository
                .findAll()
                .spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrdersEntity> findOne(Long orderNumber) {
        return ordersRepository.findById(orderNumber);
    }

    @Override
    public boolean isExists(Long orderNumber) {
        return ordersRepository.existsById(orderNumber);
    }
}
