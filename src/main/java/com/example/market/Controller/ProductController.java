package com.example.market.Controller;

import com.example.market.dto.*;
import com.example.market.entity.Product;
import com.example.market.entity.ProductImage;
import com.example.market.entity.User;
import com.example.market.service.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/product")
@RestController()
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    S3Service s3Service;

    @Autowired
    TradeService tradeService;
    @Autowired
    ProductImageService productImageService;

    //사용자가 올린 상품들 목록 가져오기
    @GetMapping("/myProducts")
    public ResponseEntity getMyProducts(
            @RequestParam("userId") Long userId){

        List<Product> productList = userService.getUser(userId).getProduct();
        if(productList.size() == 0){
            return ResponseEntity
                    .noContent()
                    .build();
        }
        List<ProductRespondDto> productRespondDtoList = new ArrayList<>();
        for(int i = 0 ; i<productList.size() ; i++){
            productRespondDtoList.add(ProductRespondDto.from(productList.get(i)));
        }
        return ResponseEntity.ok(productRespondDtoList);
    }

    @GetMapping
    public ResponseEntity getProducts(@RequestParam("keyword") String keyword,
                                      @RequestParam("userId") Long userId){

        System.out.println("keyword : "+keyword);

        List<Product> productList = productService.getProducts(keyword,userId);
        System.out.println("productList 목록들 : "+productList.size());
        if(productList.size() == 0){
            return ResponseEntity
                    .noContent()
                    .build();
        }
        List<ProductRespondDto> productRespondDtoList = new ArrayList<>();
        for(int i = 0 ; i<productList.size() ; i++){
            productRespondDtoList.add(ProductRespondDto.from(productList.get(i)));
        }
        return ResponseEntity.ok(productRespondDtoList);
    }

    @GetMapping("/saveImage")
    public ResponseEntity saveImage(@RequestParam("filename") String filename){
        System.out.println("image 요청 들어옴");
        PresignedUrlResponse presignedUrlResponse = s3Service.getPresignedUrl(filename);
        System.out.println("presignedUrlResponse uploadurl : "+presignedUrlResponse.getUploadUrl()+" ,imageurl : "+presignedUrlResponse.getImageUrl());
        return ResponseEntity.ok(presignedUrlResponse);
    }




    @PostMapping
    public ResponseEntity addProduct(ProductDto productDto,
                                     @RequestParam("userId") Long userId,
                                     @RequestParam("url") String url){

        User user = userService.getUser(userId);
        Product product = productDto.toEntity();

        System.out.println("productDto : "+productDto);
        System.out.println("product : "+product);

        product.setSeller(user);

        long productId = productService.addProduct(product);

        //productimage 가져오기
        ProductImage productImage = productImageService.saveProductImage(url, productId);


        return ResponseEntity.ok("상품 추가 완료");

    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody UpdateProductDto updateProductDto){
        System.out.println("updateProduct 요청옴");
        User user = userService.getUser(updateProductDto.getUserId());
        Product product = updateProductDto.getProductDto().toEntity();
        product.setSeller(user);

        productImageService.updateProductImages(updateProductDto.getProductId(),
                updateProductDto.getWrapperUpdateProductImageDto().getUpdateProductImageDtoList());

        productService.updateProduct(product,updateProductDto.getProductId());

        return ResponseEntity.ok("상품 추가 완료");

    }

    @DeleteMapping
    public ResponseEntity deleteProduct(
                                        @RequestParam("productId") Long productId){

       productService.deleteProduct(productId);
       return ResponseEntity.ok("상품 제거 완료");

    }



}
