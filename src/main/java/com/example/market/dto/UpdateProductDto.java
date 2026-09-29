package com.example.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDto {

    private ProductDto productDto;
    private Long userId;
    private WrapperUpdateProductImageDto wrapperUpdateProductImageDto;
    private Long productId;
}
