package com.example.market.entity;

import jakarta.persistence.*;

import java.awt.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false , length = 100)
    private String name;


    @Column(nullable = false , length = 100)
    private String addressName;

    //위도
    @Column(nullable = false)
    private double lat;

    //경도
    @Column(nullable = false)
    private double lng;

    @Column(nullable = false , columnDefinition = "POINT SRID 4326")
    private Point location;

    @ManyToOne
    @JoinColumn(name = "seller_id" , nullable = false)
    private User seller;

    @OneToMany(mappedBy = "product")
    private Trade trade;

    @OneToMany(mappedBy = "product")
    private ProductImage productImage;


}
