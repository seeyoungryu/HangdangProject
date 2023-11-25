package com.example.hangdang.entity;

import com.example.hangdang.dto.GoodsRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goodsTitle;

    private String imageURL;

    private String wishLocation;

    private Integer price;

    private boolean haveStock;

    private String content;

    private Integer likeCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany
    @JoinColumn(name = "goods_id")
    private List<Comment> commentList = new ArrayList<>();

    public Goods(String goodsTitle, String wishLocation, Integer price, boolean haveStock, String content)
    {
        this.goodsTitle = goodsTitle;
        this.wishLocation = wishLocation;
        this.price = price;
        this.haveStock = haveStock;
        this.content = content;
    }

    public Goods(GoodsRequestDto requestDto, UserEntity user)
    {
        this.goodsTitle = requestDto.getGoodsTitle();
        this.imageURL = requestDto.getImgURL();
        this.wishLocation = requestDto.getWishLocation();
        this.price = requestDto.getPrice();
        this.content = requestDto.getContent();
        this.user = user;
        this.haveStock = true;
        this.likeCount = 0;
    }

    public void updateGoods(GoodsRequestDto requestDto)
    {
        this.goodsTitle = requestDto.getGoodsTitle();
        this.imageURL = requestDto.getImgURL();
        this.wishLocation = requestDto.getWishLocation();
        this.price = requestDto.getPrice();
        this.content = requestDto.getContent();
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
