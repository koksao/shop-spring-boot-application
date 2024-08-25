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

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductQuantityEntity> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomersEntity customer;

    private Double totalPrice;

    public void addProductQuantity(ProductQuantityEntity productQuantityEntity){
        products.add(productQuantityEntity);
        productQuantityEntity.setOrder(this);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        OrdersEntity ordersEntity = (OrdersEntity) o;
        return Objects.equals(orderNumber, ordersEntity.orderNumber)&&
                Objects.equals(products, ordersEntity.products)&&
                Objects.equals(customer, ordersEntity.customer)&&
                Objects.equals(totalPrice, ordersEntity.totalPrice);
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderNumber, products, customer, totalPrice);
    }

    @Override
    public String toString() {
        return "OrdersEntity{" +
                "orderNumber=" + orderNumber +
                ", totalProducts=" + (products != null ? products.size() : 0) +
                '}';
    }
}
