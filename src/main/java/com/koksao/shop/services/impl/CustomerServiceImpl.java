package com.koksao.shop.services.impl;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.repositories.CustomersRepository;
import com.koksao.shop.services.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomersRepository customersRepository;

    public CustomerServiceImpl(CustomersRepository customersRepository){
        this.customersRepository = customersRepository;
    }


    @Override
    public CustomersEntity createCustomer(CustomersEntity customersEntity) {
        return customersRepository.save(customersEntity);
    }

}
