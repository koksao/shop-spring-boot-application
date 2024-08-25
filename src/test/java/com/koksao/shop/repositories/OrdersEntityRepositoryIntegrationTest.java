package com.koksao.shop.repositories;

import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.OrdersEntity;
import com.koksao.shop.domain.ProductQuantityEntity;
import com.koksao.shop.domain.ProductsEntity;
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
public class OrdersEntityRepositoryIntegrationTest {

    private OrdersRepository underTest;
    private ProductsRepository productsRepository;
    private CustomersRepository customersRepository;
    private ProductQuantityRepository productQuantityRepository;

    @Autowired
    public OrdersEntityRepositoryIntegrationTest(OrdersRepository underTest, ProductsRepository productsRepository, CustomersRepository customersRepository, ProductQuantityRepository productQuantityRepository){
        this.underTest = underTest;
        this.productsRepository = productsRepository;
        this.customersRepository = customersRepository;
        this.productQuantityRepository = productQuantityRepository;
    }


    @Test
    public void testThatOrderCanBeCreatedAndRecalled(){
        CustomersEntity customer = TestDataUtil.createTestCustomerA();
        customersRepository.save(customer);
        ProductsEntity product = TestDataUtil.createTestProductA();
        ProductsEntity product2 = TestDataUtil.createTestProductB();
        productsRepository.save(product);
        productsRepository.save(product2);
        OrdersEntity order = TestDataUtil.createTestOrderA(customer);
        ProductQuantityEntity productQuantityEntity = TestDataUtil.createTestProductQuantityA(product);
        ProductQuantityEntity productQuantityEntity2 = TestDataUtil.createTestProductQuantityB(product2);
        List<ProductQuantityEntity> list = new ArrayList<>();
        order.setProducts(list);
        order.addProductQuantity(productQuantityEntity);
        order.addProductQuantity(productQuantityEntity2);
        underTest.save(order);

        Optional<OrdersEntity> result = underTest.findById(order.getOrderNumber());
        assertThat(result).isPresent();
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(order);
    }

    @Test
    public void testThatOrderCanBeDeleted(){
        CustomersEntity customer = TestDataUtil.createTestCustomerA();
        customersRepository.save(customer);
        ProductsEntity product = TestDataUtil.createTestProductA();
        ProductsEntity product2 = TestDataUtil.createTestProductB();
        productsRepository.save(product);
        productsRepository.save(product2);
        OrdersEntity order = TestDataUtil.createTestOrderA(customer);
        ProductQuantityEntity productQuantityEntity = TestDataUtil.createTestProductQuantityA(product);
        ProductQuantityEntity productQuantityEntity2 = TestDataUtil.createTestProductQuantityB(product2);
        List<ProductQuantityEntity> list = new ArrayList<>();
        order.setProducts(list);
        order.addProductQuantity(productQuantityEntity);
        order.addProductQuantity(productQuantityEntity2);
        underTest.save(order);
        underTest.deleteById(order.getOrderNumber());

        Optional<OrdersEntity> result = underTest.findById(order.getOrderNumber());
        assertThat(result).isEmpty();
    }
}
