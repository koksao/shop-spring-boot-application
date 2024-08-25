package com.koksao.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Proxy(lazy = false)
@Table(name = "products")
public class ProductsEntity {

    public enum Clothes{
        SHIRT,
        PANTS
    }

    public enum Season{
        SUMMER,
        WINTER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_id_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Clothes clothes;

    private int price;

    private int availability;

    private String color;

    @Enumerated(EnumType.STRING)
    private Season season;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity product = (ProductsEntity) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(season, product.season) &&
                Objects.equals(price, product.price) &&
                Objects.equals(availability, product.availability)&&
                Objects.equals(color, product.color)&&
                Objects.equals(clothes, product.clothes);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id,season,price,availability,color,clothes);
    }
}

