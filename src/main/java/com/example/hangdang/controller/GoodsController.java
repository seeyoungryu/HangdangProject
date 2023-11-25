package com.example.hangdang.controller;

import com.example.hangdang.dto.GoodsInfoResponseDto;
import com.example.hangdang.dto.GoodsRequestDto;
import com.example.hangdang.dto.GoodsResponseDto;
import com.example.hangdang.security.UserDetailsImpl;
import com.example.hangdang.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping("/goods")
    public List<GoodsResponseDto> getGoodsList()
    {
        return goodsService.getGoodsList();
    }

    @PostMapping("/goods")
    public ResponseEntity<String> uploadGoods(@RequestBody GoodsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        try{
            goodsService.uploadGoods(requestDto, userDetails.getUser());
        } catch (Exception e){
            return new ResponseEntity<>("게시글 등록을 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("게시글을 등록하였습니다.", HttpStatus.OK);
    }

    @PutMapping("/goods/{goodsId}")
    public ResponseEntity<String> updateGoods(@PathVariable Long goodsId, @RequestBody GoodsRequestDto requestDto,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        try{
            goodsService.updateGoods(goodsId, requestDto, userDetails.getUser());
        } catch (Exception e){
            return new ResponseEntity<>("게시글 수정을 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("수정이 완료되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/goods/{goodsId}")
    public ResponseEntity<String> deleteGoods(@PathVariable Long goodsId, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        try{
            goodsService.deleteGoods(goodsId, userDetails.getUser());
        } catch (Exception e){
            return new ResponseEntity<>("게시글 삭제를 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("삭제가 완료되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/goods/{goodsId}")
    public GoodsInfoResponseDto getGoodsInfo(@PathVariable Long goodsId)
    {
        return goodsService.getGoodsInfo(goodsId);
    }

    /*
    @PostMapping("/goods/{goodsId}")
    public ResponseEntity<String> addGoodsImage()
    {

    }*/
}
