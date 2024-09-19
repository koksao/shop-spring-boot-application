package com.koksao.shop.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.ProductsEntity;
import com.koksao.shop.services.ProductService;
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
public class ProductControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ProductService productService;

    @Autowired
    public ProductControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, ProductService productService){
        this.productService = productService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateProductReturnsHttp201Created() throws Exception {
        ProductsEntity productsEntity = TestDataUtil.createTestProductA();
        String productJson = objectMapper.writeValueAsString(productsEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatListProductsReturnsHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void testThatListProductsReturnsListOfCustomers() throws Exception {
        ProductsEntity productsEntity = TestDataUtil.createTestProductC();
        productService.createProduct(productsEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].price").value(20)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].color").value("Red")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].clothes").value("PANTS")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].season").value("SUMMER")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].availability").value(100)
        );

    }

    @Test
    public void testThatGetProductReturnsHttpStatus200OkWhenProductExists() throws Exception {
        ProductsEntity productsEntity = TestDataUtil.createTestProductB();
        productService.createProduct(productsEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/" + productsEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetProductReturnsHttpStatus404OkWhenProductDoesntExists() throws Exception {
        ProductsEntity productsEntity = TestDataUtil.createTestProductB();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/" + productsEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }



}
