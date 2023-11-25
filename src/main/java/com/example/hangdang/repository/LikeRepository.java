package com.example.hangdang.repository;

import com.example.hangdang.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // 좋아요 총 개수를 가져오는 메소드
    int countByGoodsId(Long goodsId);
}
