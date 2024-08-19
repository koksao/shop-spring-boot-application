package com.koksao.shop.repositories;

import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.Products;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductsRepositoryIntegrationTest {
    private ProductsRepository underTest;

    @Autowired
    public ProductsRepositoryIntegrationTest(ProductsRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void TestThatProductCanBeCreatedAndRecalled(){
        Products product = TestDataUtil.createTestProductA();
        underTest.save(product);
        Optional<Products> result = underTest.findById(product.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(product);
    }

    @Test
    public void TestThatMultipleProductsCanBeCreatedAndRecalled(){
        Products product1 = TestDataUtil.createTestProductA();
        Products product2 = TestDataUtil.createTestProductB();
        Products product3 = TestDataUtil.createTestProductC();
        underTest.save(product1);
        underTest.save(product2);
        underTest.save(product3);
        Iterable<Products> result = underTest.findAll();
        assertThat(result).hasSize(3).containsExactly(product1,product2,product3);

    }


}
