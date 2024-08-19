package com.koksao.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_orderNumber_seq")
    private Long orderNumber;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductQuantity> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    private Double totalPrice;

    public void addProductQuantity(ProductQuantity productQuantity){
        products.add(productQuantity);
        productQuantity.setOrder(this);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderNumber,orders.orderNumber)&&
                Objects.equals(products, orders.products)&&
                Objects.equals(customer, orders.customer)&&
                Objects.equals(totalPrice, orders.totalPrice);
    }

    @Override
    public int hashCode(){
        return Objects.hash(orderNumber, products, customer, totalPrice);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderNumber=" + orderNumber +
                ", totalProducts=" + (products != null ? products.size() : 0) +
                '}';
    }
}
