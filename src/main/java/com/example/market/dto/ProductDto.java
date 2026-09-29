package com.example.market.dto;


import com.example.market.entity.Product;
import com.example.market.entity.ProductImage;
import com.example.market.entity.Trade;
import com.example.market.entity.User;
import com.example.market.service.GeometryUtil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@ToString
public class ProductDto {


    private int price;

    private String name;

    private String addressName;


    private String description;

    //위도
    private double lat;

    //경도
    private double lng;

    //private List<ProductImage> productImageList;

    public Product toEntity(){
        return Product.builder()
                .price(this.price)
                .name(this.name)
                .addressName(this.addressName)
                .description(this.description)
                .lat(this.lat)
                .lng(this.lng)
                .location(GeometryUtil.createPoint(this.lat,this.lng))
                .build();

    }

}
