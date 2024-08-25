package com.koksao.shop.repositories;

import com.koksao.shop.domain.CustomersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends CrudRepository<CustomersEntity,Long> {
}
