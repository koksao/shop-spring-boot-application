package com.koksao.shop.repositories;

import com.koksao.shop.domain.ProductQuantityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductQuantityRepository extends CrudRepository<ProductQuantityEntity, Long> {
}
