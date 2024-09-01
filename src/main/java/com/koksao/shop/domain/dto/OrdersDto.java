package com.koksao.shop.domain.dto;


import com.koksao.shop.domain.CustomersEntity;
import com.koksao.shop.domain.ProductQuantityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersDto {

    private Long orderNumber;

    private Long customerId;

    private List<ProductQuantityDto> productsQuantities;
}
