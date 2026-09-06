package com.example.market.entity;

import jakarta.persistence.*;

import java.awt.*;

@Entity
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false ,
        columnDefinition = "state CHECK IN ('PENDING' , 'PURCHASE_SUCCESS' , 'PURCHASE_FAILED' )")
    private String state;


    @ManyToOne
    @JoinColumn(name = "product_id" , nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id" , nullable = false)
    private User buyer;


}
