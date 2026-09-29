package com.example.market.dto;

import com.example.market.entity.Product;
import com.example.market.entity.Trade;
import com.example.market.entity.User;
import com.example.market.service.GeometryUtil;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {



    private String email;

    private String name;

    private String password;

    private String addressName;

    private double lat;

    private double lng;

    private double mannerTemp;

   public User toEntity(){
        return User.builder()
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .addressName(this.addressName)
                .lat(this.lat)
                .lng(this.lng)
                .mannerTemp(this.mannerTemp)
                .location(GeometryUtil.createPoint(this.lat, this.lng))
                .build();

    }

}
