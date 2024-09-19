package com.koksao.shop.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.services.CustomerService;
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
public class CustomerControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private CustomerService customerService;

    @Autowired
    public CustomerControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, CustomerService customerService){
        this.customerService = customerService;
        this.mockMvc =mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCreateCustomerReturnsHttp201Created() throws Exception {
        CustomersEntity testCustomer = TestDataUtil.createTestCustomerA();
        String customerJson = objectMapper.writeValueAsString(testCustomer);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatListCustomerReturnsHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testThatListCustomerReturnsListOfCustomers() throws Exception {
        CustomersEntity customersEntity = TestDataUtil.createTestCustomerA();
        customerService.createCustomer(customersEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].firstName").value("Bob")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].lastName").value("Budowniczy")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].email").value("bobbb11@gmail.com")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("748454985")
        );

    }

    @Test
    public void testThatGetCustomerReturnsHttpStatus200OkWhenCustomerExists() throws Exception {
        CustomersEntity customer = TestDataUtil.createTestCustomerA();
        customerService.createCustomer(customer);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetCustomerReturnsHttpStatus404OkWhenCustomerDoesntExists() throws Exception {
        CustomersEntity customer = TestDataUtil.createTestCustomerA();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
