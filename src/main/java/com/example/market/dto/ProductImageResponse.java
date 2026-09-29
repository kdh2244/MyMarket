package com.example.market.dto;

import com.example.market.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponse {
    private Long id;
    private String img;

    static public ProductImageResponse from(ProductImage productImage){
        ProductImageResponse productImageResponse = new ProductImageResponse();
        productImageResponse.id = productImage.getId();
        productImageResponse.img = productImage.getImg();
        return productImageResponse;
    }

}
