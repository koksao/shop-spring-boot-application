package com.koksao.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_orderNumber_seq")
    private Long orderNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        OrdersEntity ordersEntity = (OrdersEntity) o;
        return Objects.equals(orderNumber, ordersEntity.orderNumber)&&
                Objects.equals(customer, ordersEntity.customer);
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderNumber, customer);
    }

    @Override
    public String toString() {
        return "OrdersEntity{" +
                "orderNumber=" + orderNumber;
    }
}
