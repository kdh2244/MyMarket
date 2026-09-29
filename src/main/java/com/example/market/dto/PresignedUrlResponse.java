package com.example.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PresignedUrlResponse {

    private String uploadUrl;
    private String imageUrl;

}
