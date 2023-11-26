package com.example.hangdang.service;

import com.example.hangdang.repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void like(Long userId, Long goodsId) {
        // 좋아요 로직 구현

    }


    public void unlike(Long userId, Long goodsId) {
        // 좋아요 취소 로직 구현
    }


    public int getTotalLikes(Long goodsId) {
        return likeRepository.countByGoodsId(goodsId);
    }
}