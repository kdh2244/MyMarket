package com.example.market.service;

import com.example.market.dto.ProductDto;
import com.example.market.entity.Product;
import com.example.market.entity.Trade;
import com.example.market.entity.User;
import com.example.market.repository.ProductRepository;
import com.example.market.repository.TradeRepository;
import com.example.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TradeService {
    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public void createTrade(Long userId, Long productId){

        // 구매자 추가 , 상품 추가 한 객체 생성 , 상태
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 객체입니다."));
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 객체입니다."));;

        Trade trade = new Trade();
        trade.setState("PENDING");
        trade.setBuyer(user);
        trade.setProduct(product);

        tradeRepository.save(trade);

    }

    public List<Trade> getTrades(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 객체입니다."));;
        List<Trade> tradeList = user.getTrade();
        return tradeList;
    }


    @Transactional
    public void acceptTrade(Long tradeId){

       Trade trade = tradeRepository.findById(tradeId)
               .orElseThrow(()->new IllegalArgumentException("존재하지 않는 객체입니다."));;
        trade.setState("PURCHASE_SUCCESS");
    }

    @Transactional
    public void refuseTrade(Long tradeId){

        Trade trade = tradeRepository.findById(tradeId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 객체입니다."));;
        trade.setState("PURCHASE_FAILED");
    }
}
