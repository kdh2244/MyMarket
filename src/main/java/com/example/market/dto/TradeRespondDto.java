package com.example.market.dto;

import com.example.market.entity.Product;
import com.example.market.entity.Trade;
import com.example.market.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeRespondDto {

    private Long id;
    private String state;

    private ProductRespondDto productRespondDto;

    static public TradeRespondDto from(Trade trade){
        TradeRespondDto tradeRespondDto = new TradeRespondDto();
        tradeRespondDto.id = trade.getId();
        tradeRespondDto.state = trade.getState();

        tradeRespondDto.productRespondDto = ProductRespondDto.from(trade.getProduct());


        return tradeRespondDto;
    }


}
