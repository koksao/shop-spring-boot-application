package com.koksao.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomersDto {

    private Long id;

    private String firstName;

    private String  lastName;

    private String email;

    private int phoneNumber;
}
