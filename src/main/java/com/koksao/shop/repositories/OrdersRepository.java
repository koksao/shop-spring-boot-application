package com.koksao.shop.repositories;

import com.koksao.shop.domain.OrdersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<OrdersEntity,Long> {
}
