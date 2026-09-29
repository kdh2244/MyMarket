package com.example.market.entity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,
    columnDefinition = " INT CHECK (price >= 0)")
    private int price;

    @Column(nullable = false , length = 100)
    private String name;


    @Column(nullable = false , length = 100)
    private String addressName;

    @Column(nullable = false , length = 400)
    private String description;

    //위도
    @Column(nullable = false)
    private double lat;

    //경도
    @Column(nullable = false)
    private double lng;

    @Column(nullable = false , columnDefinition = "POINT SRID 4326")
    private Point location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id" , nullable = false)
    private User seller;

    @OneToMany(mappedBy = "product")
    private List<Trade> tradeList;

    @OneToMany(mappedBy = "product" ,cascade = CascadeType.REMOVE ,
    orphanRemoval = true)
    private List<ProductImage> productImageList;


}
