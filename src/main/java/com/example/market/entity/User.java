package com.example.market.entity;

import jakarta.persistence.*;

import java.awt.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,
        columnDefinition = "VARCHAR(100) email CHECK IN ('%@%')")
    private String email;

    @Column(nullable = false , length = 100)
    private String name;

    @Column(nullable = false , length = 100 , unique = true)
    private String password;

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

    @Column(nullable = false)
    private double mannerTemp;

    @OneToMany(mappedBy = "seller")
    private Product product;

    @OneToMany(mappedBy = "buyer")
    private Trade trade;




}
