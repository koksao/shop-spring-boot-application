package com.koksao.shop.services;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.dto.CustomersDto;

public interface CustomerService {


    CustomersEntity createCustomer(CustomersEntity customer);
}
