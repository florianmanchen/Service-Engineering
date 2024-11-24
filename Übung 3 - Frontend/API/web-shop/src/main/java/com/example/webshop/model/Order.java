package com.example.webshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @ManyToMany
    List<Product> products;

    @Column(name = "price")
    int price;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
