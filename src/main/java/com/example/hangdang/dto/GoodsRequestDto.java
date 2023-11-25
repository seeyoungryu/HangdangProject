package com.example.hangdang.dto;

import lombok.Getter;

@Getter
public class GoodsRequestDto {
    private String goodsTitle;
    private String imgURL;
    private String wishLocation;
    private Integer price;
    private String content;
}
