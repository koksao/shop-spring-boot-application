package com.koksao.shop.services;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.dto.CustomersDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {


    CustomersEntity createCustomer(CustomersEntity customer);

    List<CustomersEntity> findAll();

    Optional<CustomersEntity> findOne(Long id);
}
