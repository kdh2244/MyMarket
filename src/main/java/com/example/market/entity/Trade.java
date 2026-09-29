package com.example.market.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Entity
@Getter
@Setter
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false ,
        columnDefinition = "VARCHAR(30) CHECK ( state IN ('PENDING' , 'PURCHASE_SUCCESS' , 'PURCHASE_FAILED' ))" )
    private String state;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id" , nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id" , nullable = false)
    private User buyer;


}
