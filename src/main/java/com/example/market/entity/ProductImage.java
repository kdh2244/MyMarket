package com.example.market.entity;

import jakarta.persistence.*;

import java.awt.*;

@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 300)
    private String img;

    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false)
    private Product product;

}
