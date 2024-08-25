package com.koksao.shop.repositories;

import com.koksao.shop.domain.ProductsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsEntity, Long> {
}
