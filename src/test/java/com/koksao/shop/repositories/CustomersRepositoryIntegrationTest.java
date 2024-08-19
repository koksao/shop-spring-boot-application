package com.koksao.shop.repositories;

import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.Customers;
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
public class CustomersRepositoryIntegrationTest {

    private CustomersRepository underTest;

    @Autowired
    public CustomersRepositoryIntegrationTest(CustomersRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatCustomerCanBeCreatedAndRecall(){
        Customers customer = TestDataUtil.createTestCustomerA();
        underTest.save(customer);
        Optional<Customers> result = underTest.findById(customer.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(customer);

    }

    @Test
    public void testThatMultipleCustomersCanBeCreatedAndRecall(){
        Customers customer1 = TestDataUtil.createTestCustomerA();
        Customers customer2 = TestDataUtil.createTestCustomerB();
        Customers customer3 = TestDataUtil.createTestCustomerC();
        underTest.save(customer1);
        underTest.save(customer2);
        underTest.save(customer3);

        Iterable<Customers> result = underTest.findAll();
        assertThat(result)
                .hasSize(3).
                containsExactly(customer1,customer2,customer3);
    }



}
