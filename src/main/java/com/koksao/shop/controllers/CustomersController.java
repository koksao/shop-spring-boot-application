package com.koksao.shop.controllers;

import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.dto.CustomersDto;
import com.koksao.shop.mappers.Mapper;
import com.koksao.shop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/customers")
    public List<CustomersDto> listCustomers(){
        List<CustomersEntity> customers = customerService.findAll();
        return customers.stream()
                .map(customersMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/customers/{id}")
    public ResponseEntity<CustomersDto> getCustomer(@PathVariable("id") Long id){
        Optional<CustomersEntity> foundCustomer = customerService.findOne(id);
        return foundCustomer.map(customersEntity -> {
            CustomersDto customersDto = customersMapper.mapTo(customersEntity);
            return new ResponseEntity<>(customersDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/customers/{id}")
    public ResponseEntity<CustomersDto> fullUpdateCustomer
            (@PathVariable("id") Long id, @RequestBody CustomersDto customersDto){
    if(!customerService.isExists(id)){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    customersDto.setId(id);
    CustomersEntity customerEntity =  customersMapper.mapFrom(customersDto);
    CustomersEntity savedCustomerEntity = customerService.createCustomer(customerEntity);

    return new ResponseEntity<>(customersMapper.mapTo(savedCustomerEntity), HttpStatus.OK);

    }

    @DeleteMapping(path = "/customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
