package com.example.market.dto;

import com.example.market.entity.Product;
import com.example.market.entity.Trade;
import com.example.market.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRespondDto {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String addressName;

    private double lat;

    private double lng;

    private double mannerTemp;

    private List<ProductRespondDto> productRespondDtoList;

    private List<TradeRespondDto> tradeRespondDtoList;

    UserRespondDto from(User user){

        UserRespondDto  userRespondDto = new UserRespondDto();
        List<Product> productList = user.getProduct();
        List<Trade> tradeList = user.getTrade();

        for(int i = 0 ; i<productList.size() ; i++){
            userRespondDto.productRespondDtoList.add(ProductRespondDto.from(productList.get(i)));
        }

        for(int i = 0 ; i<tradeList.size() ; i++){
            userRespondDto.tradeRespondDtoList.add(TradeRespondDto.from(tradeList.get(i)));
        }


        userRespondDto.id = user.getId();
        userRespondDto.email = user.getEmail();
        userRespondDto.name = user.getName();
        userRespondDto.password = user.getPassword();
        userRespondDto.addressName = user.getAddressName();
        userRespondDto.lat = user.getLat();
        userRespondDto.lng = user.getLng();
        userRespondDto.mannerTemp = user.getMannerTemp();

        return userRespondDto;

    }

}
