package com.example.market.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.example.market.entity.Product;
import com.example.market.entity.User;
import com.example.market.repository.ProductRepository;
import com.example.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public List<Product> getProducts(String keyword,Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 객체입니다."));

        List<Product> productList = productRepository.findProductsWithinRadius(user.getLocation(),3000000,keyword);
        return productList;

    }



    public long addProduct(Product product){
        Product product1 = productRepository.save(product);
        return product1.getId();
    }

    @Transactional
    public void updateProduct(Product updateProduct,Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 객체입니다."));
        product.setLng(updateProduct.getLng());
        product.setLat(updateProduct.getLat());
        product.setName(updateProduct.getName());
        product.setLocation(updateProduct.getLocation());
        product.setPrice(updateProduct.getPrice());
        product.setDescription(updateProduct.getDescription());
        product.setAddressName(updateProduct.getAddressName());
        product.setSeller(updateProduct.getSeller());
        product.setProductImageList(updateProduct.getProductImageList());

        productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 객체입니다."));
        productRepository.delete(product);

    }


    public void save() {

    }
}
