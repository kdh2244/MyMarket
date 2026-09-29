package com.example.market.dto;

import com.example.market.entity.Product;
import com.example.market.entity.ProductImage;
import com.example.market.entity.Trade;
import com.example.market.entity.User;
import com.example.market.repository.ProductRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRespondDto {

    private Long id;
    private int price;
    private String name;
    private String addressName;
    private String description;
    private double lat;
    private double lng;

    private List<ProductImageResponse> productImageResponseList = new ArrayList<>();

    static public ProductRespondDto from(Product product){

        ProductRespondDto productRespondDto = new ProductRespondDto();
        productRespondDto.id = product.getId();
        productRespondDto.price = product.getPrice();
        productRespondDto.name = product.getName();
        productRespondDto.description = product.getDescription();
        productRespondDto.lat = product.getLat();
        productRespondDto.lng = product.getLng();
        productRespondDto.addressName = product.getAddressName();

        List<ProductImage> productImageList = product.getProductImageList();
        System.out.println("productImageList : "+productImageList.get(0).getImg());

        for(int i = 0 ;i<productImageList.size();i++){
            productRespondDto.productImageResponseList.add(ProductImageResponse.from(productImageList.get(i)));
            System.out.println("productImageResponseList : "+ i +" "+ productRespondDto.getProductImageResponseList().get(i).getImg());
        }



        return productRespondDto;

    }

}
