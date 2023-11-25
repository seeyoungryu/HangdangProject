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

    // 좋아요 등록
    @PostMapping("/goods/{goodsId}/like")
    public ResponseEntity<String> like(@PathVariable Long goodsId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try{
            likeService.like(goodsId, userDetails.getUser());
        } catch (Exception e){
            return new ResponseEntity<>("좋아요를 등록하는 데에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("좋아요가 등록되었습니다.", HttpStatus.OK);
    }

    // 좋아요 취소
    @DeleteMapping("/goods/{goodsId}/like/{likeId}")
    public ResponseEntity<String> unlike(@PathVariable Long goodsId, @PathVariable Long likeId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try{
            likeService.unlike(goodsId, likeId, userDetails.getUser());
        } catch (Exception e){
            return new ResponseEntity<>("좋아요를 취소하는 데에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("좋아요가 취소되었습니다.", HttpStatus.OK);
    }
}
