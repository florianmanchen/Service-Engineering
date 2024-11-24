package com.example.webshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "price")
    int price;

    @Column(name = "img")
    String img;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
