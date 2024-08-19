package com.koksao.shop.repositories;

import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.Customers;
import com.koksao.shop.domain.Orders;
import com.koksao.shop.domain.ProductQuantity;
import com.koksao.shop.domain.Products;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrdersRepositoryIntegrationTest {

    private OrdersRepository underTest;
    private ProductsRepository productsRepository;
    private CustomersRepository customersRepository;
    private ProductQuantityRepository productQuantityRepository;

    @Autowired
    public OrdersRepositoryIntegrationTest(OrdersRepository underTest, ProductsRepository productsRepository, CustomersRepository customersRepository, ProductQuantityRepository productQuantityRepository){
        this.underTest = underTest;
        this.productsRepository = productsRepository;
        this.customersRepository = customersRepository;
        this.productQuantityRepository = productQuantityRepository;
    }


    @Test
    public void testThatOrderCanBeCreatedAndRecalled(){
        Customers customer = TestDataUtil.createTestCustomerA();
        customersRepository.save(customer);
        Products product = TestDataUtil.createTestProductA();
        Products product2 = TestDataUtil.createTestProductB();
        productsRepository.save(product);
        productsRepository.save(product2);
        Orders order = TestDataUtil.createTestOrderA(customer);
        ProductQuantity productQuantity = TestDataUtil.createTestProductQuantityA(product);
        ProductQuantity productQuantity2 = TestDataUtil.createTestProductQuantityB(product2);
        List<ProductQuantity> list = new ArrayList<>();
        order.setProducts(list);
        order.addProductQuantity(productQuantity);
        order.addProductQuantity(productQuantity2);
        underTest.save(order);




        Optional<Orders> result = underTest.findById(order.getOrderNumber());
        assertThat(result).isPresent();
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(order);
    }
}
