package com.koksao.shop.domain.dto;


import com.koksao.shop.domain.ProductsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductQuantityDto {

    private Long productId;

    private int quantity;
}
