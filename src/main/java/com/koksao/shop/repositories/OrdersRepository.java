package com.koksao.shop.repositories;

import com.koksao.shop.domain.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Orders,Long> {
}
