package com.example.hangdang.dto;

import com.example.hangdang.entity.Comment;
import com.example.hangdang.entity.Goods;
import lombok.Getter;

import java.util.List;

@Getter
public class GoodsInfoResponseDto {
    private Long id;
    private String goodsTitle;
    private Integer price;
    private String imageURL;
    private String wishLocation;
    private Integer likeCount;
    private boolean haveStock;
    private List<Comment> commentList;

    public GoodsInfoResponseDto(Goods goods)
    {
        this.id = goods.getId();
        this.goodsTitle = goods.getGoodsTitle();
        this.price = goods.getPrice();
        this.imageURL = goods.getImageURL();
        this.wishLocation = goods.getWishLocation();
        this.likeCount = goods.getLikeCount();
        this.haveStock = goods.isHaveStock();
        this.commentList = goods.getCommentList();
    }

}
