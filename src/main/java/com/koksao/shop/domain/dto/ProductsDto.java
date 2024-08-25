package com.koksao.shop.domain.dto;

import com.koksao.shop.domain.ProductsEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsDto {

    private Long id;

    private ProductsEntity.Clothes clothes;

    private int price;

    private int availability;

    private String color;

    private ProductsEntity.Season season;
}
