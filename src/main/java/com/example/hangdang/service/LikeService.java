package com.example.hangdang.service;

import com.example.hangdang.entity.Goods;
import com.example.hangdang.entity.UserEntity;
import com.example.hangdang.repository.GoodsRepository;
import com.example.hangdang.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final GoodsRepository goodsRepository;

    public void likeGoods(Long goodsId, UserEntity user) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()-> new NullPointerException("게시글이 존재하지 않습니다.")
        );

        goods.setLikeCount(goods.getLikeCount()+1);
    }

    public void disLikeGoods(Long goodsId, UserEntity user) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()-> new NullPointerException("게시글이 존재하지 않습니다.")
        );

        goods.setLikeCount(goods.getLikeCount()-1);
    }
}
