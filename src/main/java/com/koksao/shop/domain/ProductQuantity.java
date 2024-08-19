package com.koksao.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "productQuantity")
public class ProductQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productQuantity_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    private int quantity;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantity that = (ProductQuantity) o;
        return Objects.equals(quantity, that.quantity)&&
                Objects.equals(product, that.product);
    }
    @Override
    public int hashCode() {
        return Objects.hash(quantity, product);
    }

    @Override
    public String toString() {
        return "ProductQuantity{" +
                "id=" + id +
                ", product=" + (product != null ? product.getClothes() : "null") +
                ", quantity=" + quantity +
                '}';
    }

}
