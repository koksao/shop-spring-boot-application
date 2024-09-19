package com.koksao.shop.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.domain.dto.OrdersRequestDto;
import com.koksao.shop.domain.dto.ProductQuantityDto;
import com.koksao.shop.mappers.impl.OrderMapperImpl;
import com.koksao.shop.repositories.CustomersRepository;
import com.koksao.shop.repositories.ProductsRepository;
import com.koksao.shop.services.OrderService;
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
    private OrderMapperImpl orderMapper;
    private OrderService orderService;

    @Autowired
    public OrderControllerIntegrationTests(OrderService orderService, MockMvc mockMvc, ObjectMapper objectMapper, CustomersRepository customersRepository, ProductsRepository productsRepository, OrderMapperImpl orderMapper){
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
        this.customersRepository = customersRepository;
        this.productsRepository = productsRepository;
        this.orderMapper = orderMapper;
        this.orderService = orderService;
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

        OrdersRequestDto ordersRequestDto = TestDataUtil.createTestOrderDtoA(productQuantityA, productQuantityB, customer);

        String orderJson = objectMapper.writeValueAsString(ordersRequestDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatListOrderReturnsHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testThatListOrderReturnsListOfOrders() throws Exception {

        ProductsEntity productA = TestDataUtil.createTestProductA();
        ProductsEntity productB = TestDataUtil.createTestProductB();
        ProductQuantityDto productQuantityA = TestDataUtil.createTestProductQuantityDtoA(productA);
        ProductQuantityDto productQuantityB = TestDataUtil.createTestProductQuantityDtoB(productB);

        CustomersEntity customer = TestDataUtil.createTestCustomerA();
        customersRepository.save(customer);
        productsRepository.save(productA);
        productsRepository.save(productB);

        OrdersRequestDto ordersRequestDto = TestDataUtil.createTestOrderDtoA(productQuantityA, productQuantityB, customer);
        orderService.createOrder(ordersRequestDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].customerId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].orderNumber").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].customerFirstName").value("Bob")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].customerLastName").value("Budowniczy")
        );
    }

    @Test
    public void testThatGetOrderReturnsHttp200WhenOrderExists() throws Exception {

        ProductsEntity productA = TestDataUtil.createTestProductA();
        ProductsEntity productB = TestDataUtil.createTestProductB();
        ProductQuantityDto productQuantityA = TestDataUtil.createTestProductQuantityDtoA(productA);
        ProductQuantityDto productQuantityB = TestDataUtil.createTestProductQuantityDtoB(productB);

        CustomersEntity customer = TestDataUtil.createTestCustomerA();
        customersRepository.save(customer);
        productsRepository.save(productA);
        productsRepository.save(productB);

        OrdersRequestDto ordersRequestDto = TestDataUtil.createTestOrderDtoA(productQuantityA, productQuantityB, customer);
       OrdersEntity savedOrdersEntity = orderService.createOrder(ordersRequestDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/" + savedOrdersEntity.getOrderNumber())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetOrderReturnsHttp404WhenOrderDoesntExists() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/" + 39999)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
