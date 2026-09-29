package com.example.market.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.example.market.dto.updateProductImageDto;
import com.example.market.entity.Product;
import com.example.market.entity.ProductImage;
import com.example.market.repository.ProductImageRepository;
import com.example.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProductImageService {

    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    ProductRepository productRepository;



    @Transactional
    public ProductImage saveProductImage(String url, long productId){
        System.out.println("saveProductImage 저장 함수 실행");
        ProductImage productImage = new ProductImage();
        productImage.setImg(url);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재 하지 않습니다."));

        productImage.setProduct(product);
        productImageRepository.save(productImage);
        return productImage;
    }

    @Transactional
    public void updateProductImages(long productId,
                                    List<updateProductImageDto> updateProductImageList){
        //url 리스트 목록들을 받아서 productImage객체 생성해서 db저장,
        //문제는 삭제는 어케 하냐인데.
        for(int i = 0 ; i < updateProductImageList.size() ; i++){
            if(updateProductImageList.get(i).getUrl() != null){
                saveProductImage(updateProductImageList.get(i).getUrl(),productId);
            }else{
                if(updateProductImageList.get(i).isRemove()){
                    ProductImage productImage = productImageRepository.findById(updateProductImageList.get(i).getProductImageId())
                            .orElseThrow(()->new IllegalArgumentException("productimage가 없습니다."));
                    productImageRepository.delete(productImage);
                }
            }
        }
    }


}
