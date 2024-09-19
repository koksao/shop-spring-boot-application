package com.koksao.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersResponseDto {

    private Long customerId;

    private Long orderNumber;

    private String CustomerFirstName;

    private String CustomerLastName;
}
