package com.example.market.entity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.awt.*;
import java.util.List;

@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,
        columnDefinition = "VARCHAR(100) CHECK ( email LIKE '%@%' )")
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
    @Column(nullable = true)
    private List<Product> product;

    @OneToMany(mappedBy = "buyer")
    @Column(nullable = true)
    private List<Trade> trade;




}
