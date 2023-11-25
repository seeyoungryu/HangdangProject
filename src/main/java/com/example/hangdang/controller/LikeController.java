package com.example.hangdang.controller;

import com.example.hangdang.security.UserDetailsImpl;
import com.example.hangdang.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/api/goods/{goodsId}/like")
    public ResponseEntity<String> likeGoods(@PathVariable Long goodsId, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        likeService.likeGoods(goodsId, userDetails.getUser());
        return new ResponseEntity<>("좋아요를 등록하였습니다", HttpStatus.OK);
    }

    @DeleteMapping("/api/goods/{goodsId}/like")
    public ResponseEntity<String> disLikeGoods(@PathVariable Long goodsId, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        likeService.disLikeGoods(goodsId, userDetails.getUser());
        return new ResponseEntity<>("좋아요를 취소했습니다.", HttpStatus.OK);
    }
}
