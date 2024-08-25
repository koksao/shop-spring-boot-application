package com.koksao.shop.controllers;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.dto.CustomersDto;
import com.koksao.shop.mappers.Mapper;
import com.koksao.shop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomersController {

    private CustomerService customerService;
    private Mapper<CustomersEntity, CustomersDto> customersMapper;

    public CustomersController(CustomerService customerService, Mapper<CustomersEntity, CustomersDto> customersMapper){
        this.customerService = customerService;
        this.customersMapper = customersMapper;
    }

    @PostMapping(path = "/customers")
    public ResponseEntity<CustomersDto> createCustomer(@RequestBody CustomersDto customer){
        CustomersEntity customerEntity = customersMapper.mapFrom(customer);
        CustomersEntity savedCustomerEntity = customerService.createCustomer(customerEntity);
        return new ResponseEntity<>(customersMapper.mapTo(savedCustomerEntity), HttpStatus.CREATED);
    }
}
