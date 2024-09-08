package com.koksao.shop.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.domain.dto.CustomersDto;
import com.koksao.shop.domain.dto.OrdersDto;
import com.koksao.shop.domain.dto.ProductQuantityDto;
import com.koksao.shop.domain.dto.ProductsDto;
import com.koksao.shop.repositories.CustomersRepository;
import com.koksao.shop.repositories.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class OrderControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private CustomersRepository customersRepository;
    private ProductsRepository productsRepository;

    @Autowired
    public OrderControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, CustomersRepository customersRepository, ProductsRepository productsRepository){
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
        this.customersRepository = customersRepository;
        this.productsRepository = productsRepository;
    }

    @Test
    public void TestThatCreateOrderReturnsHttp201Created() throws Exception {

        ProductsEntity productA = TestDataUtil.createTestProductA();
        ProductsEntity productB = TestDataUtil.createTestProductB();
        ProductQuantityDto productQuantityA = TestDataUtil.createTestProductQuantityDtoA(productA);
        ProductQuantityDto productQuantityB = TestDataUtil.createTestProductQuantityDtoB(productB);

        CustomersEntity customer = TestDataUtil.createTestCustomerA();
        customersRepository.save(customer);
        productsRepository.save(productA);
        productsRepository.save(productB);

        OrdersDto ordersDto = TestDataUtil.createTestOrderDtoA(productQuantityA, productQuantityB, customer);

        String orderJson = objectMapper.writeValueAsString(ordersDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

}
