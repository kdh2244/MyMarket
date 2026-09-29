package com.example.market.Controller;

import com.example.market.dto.ProductRespondDto;
import com.example.market.dto.TradeRespondDto;
import com.example.market.entity.Trade;
import com.example.market.service.ProductService;
import com.example.market.service.TradeService;
import com.example.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/trade")
@RestController()
public class TradeController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    TradeService tradeService;

    @GetMapping("/trades")
    public ResponseEntity tradeRequest(
            @RequestParam("userId") Long userId){
        List<Trade> tradeList = tradeService.getTrades(userId);
        if(tradeList.size()==0){
            return ResponseEntity
                    .noContent()
                    .build();
        }
        List<TradeRespondDto> tradeRespondDtoList = new ArrayList<>();
        for(int i = 0 ; i<tradeList.size() ; i++){
            tradeRespondDtoList.add(TradeRespondDto.from(tradeList.get(i)));
        }
        return ResponseEntity.ok(tradeRespondDtoList);
    }




    @PostMapping("/request")
    public ResponseEntity tradeRequest(@RequestParam("userId") Long userId,
                                       @RequestParam("productId") Long productId){
        tradeService.createTrade(userId,productId);
        return ResponseEntity.ok("거래 요청 완료");
    }

    @PostMapping("/acceptance")
    public ResponseEntity acceptTrade(
            @RequestParam("tradeId") Long tradeId){
        tradeService.acceptTrade(tradeId);
        return ResponseEntity.ok("거래 수락 완료");
    }

    @PostMapping("/refusal")
    public ResponseEntity refuseTrade(@RequestParam("tradeId") Long tradeId){
        tradeService.refuseTrade(tradeId);
        return ResponseEntity.ok("거래 거절 완료");
    }


}
