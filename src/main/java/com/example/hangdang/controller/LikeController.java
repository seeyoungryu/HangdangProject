package com.example.hangdang.controller;

import com.example.hangdang.dto.LikeDto;
import com.example.hangdang.service.LikeService;
import com.example.hangdang.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goods")
public class LikeController {

    private final LikeService likeService;
    private final JwtUtil jwtUtil;

    public LikeController(LikeService likeService, JwtUtil jwtUtil) {
        this.likeService = likeService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/{goodsId}/like")
    public ResponseEntity<LikeDto> like(@PathVariable Long goodsId, @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.getUserIdFromToken(token);
        likeService.like(userId, goodsId);
        int totalLikes = likeService.getTotalLikes(goodsId);
        LikeDto likeDto = new LikeDto();
        likeDto.setMessage("좋아요를 등록하였습니다.");
        likeDto.setTotalLikes(totalLikes);
        return ResponseEntity.ok(likeDto);
    }

    @DeleteMapping("/{goodsId}/like")
    public ResponseEntity<LikeDto> unlike(@PathVariable Long goodsId, @RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.getUserIdFromToken(token);
        likeService.unlike(userId, goodsId);
        int totalLikes = likeService.getTotalLikes(goodsId);
        LikeDto likeDto = new LikeDto();
        likeDto.setMessage("좋아요를 취소하였습니다.");
        likeDto.setTotalLikes(totalLikes);
        return ResponseEntity.ok(likeDto);
    }
}
