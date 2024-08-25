package com.koksao.shop.repositories;

import com.koksao.shop.TestDataUtil;
import com.koksao.shop.domain.CustomersEntity;
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
public class CustomersEntityRepositoryIntegrationTest {

    private CustomersRepository underTest;

    @Autowired
    public CustomersEntityRepositoryIntegrationTest(CustomersRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatCustomerCanBeCreatedAndRecall(){
        CustomersEntity customer = TestDataUtil.createTestCustomerA();
        underTest.save(customer);
        Optional<CustomersEntity> result = underTest.findById(customer.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(customer);

    }

    @Test
    public void testThatMultipleCustomersCanBeCreatedAndRecall(){
        CustomersEntity customer1 = TestDataUtil.createTestCustomerA();
        CustomersEntity customer2 = TestDataUtil.createTestCustomerB();
        CustomersEntity customer3 = TestDataUtil.createTestCustomerC();
        underTest.save(customer1);
        underTest.save(customer2);
        underTest.save(customer3);

        Iterable<CustomersEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3).
                containsExactly(customer1,customer2,customer3);
    }

    @Test
    public void testThatCustomerCanBeUpdated(){
        CustomersEntity customer = TestDataUtil.createTestCustomerA();
        underTest.save(customer);
        customer.setFirstName("UPDATED");
        underTest.save(customer);
        Optional<CustomersEntity> result = underTest.findById(customer.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(customer);

    }

    @Test
    public void testThatCustomerCanBeDeleted(){
        CustomersEntity customer = TestDataUtil.createTestCustomerA();
        underTest.save(customer);
        underTest.deleteById(customer.getId());
        Optional<CustomersEntity> result = underTest.findById(customer.getId());
        assertThat(result).isEmpty();

    }



}
