package com.koksao.shop.services;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.dto.CustomersDto;

import java.util.List;

public interface CustomerService {


    CustomersEntity createCustomer(CustomersEntity customer);

    List<CustomersEntity> findAll();
}
