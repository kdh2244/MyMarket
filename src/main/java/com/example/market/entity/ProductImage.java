package com.example.market.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 300)
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id" , nullable = false)
    private Product product;

    public void setProduct(Product product){
        System.out.println("setProduct() 내가 만든게 실행되는 거 맞아요.");
        this.product = product;

        List<ProductImage> productImageList = new ArrayList<>();
        productImageList.add(this);
        this.product.setProductImageList(productImageList);
    }

}
