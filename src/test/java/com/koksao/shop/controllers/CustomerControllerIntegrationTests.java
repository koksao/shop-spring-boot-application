package com.koksao.shop.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.CustomersEntity;
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

    @Autowired
    public CustomerControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper){
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

}
