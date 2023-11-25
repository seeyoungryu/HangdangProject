package com.example.hangdang.service;

import com.example.hangdang.dto.GoodsInfoResponseDto;
import com.example.hangdang.dto.GoodsRequestDto;
import com.example.hangdang.dto.GoodsResponseDto;
import com.example.hangdang.entity.Goods;
import com.example.hangdang.entity.UserEntity;
import com.example.hangdang.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;


    public List<GoodsResponseDto> getGoodsList()
    {
        List<GoodsResponseDto> goodsResponseDtoList = new ArrayList<>();
        List<Goods> goodsList = goodsRepository.findAll();

        for(Goods goods : goodsList)
        {
            goodsResponseDtoList.add(new GoodsResponseDto(goods));
        }

        return goodsResponseDtoList;
    }

    public void uploadGoods(GoodsRequestDto requestDto, UserEntity user)
    {
        Goods goods = new Goods(requestDto, user);
        goodsRepository.save(goods);
    }

    public void updateGoods(Long goodsId, GoodsRequestDto requestDto, UserEntity user)
    {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()-> new NullPointerException("해당하는 게시글이 존재하지 않습니다.")
        );

        if(goods.getUser().getNickname().equals(user.getNickname()))
        {
            goods.updateGoods(requestDto);
        }

        goodsRepository.save(goods);
    }

    public void deleteGoods(Long goodsId, UserEntity user)
    {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()-> new NullPointerException("해당하는 게시글이 존재하지 않습니다.")
        );

        if(goods.getUser().getNickname().equals(user.getNickname()))
        {
            goodsRepository.delete(goods);
        }
    }

    public GoodsInfoResponseDto getGoodsInfo(Long goodsId)
    {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()-> new NullPointerException("해당하는 게시글이 존재하지 않습니다.")
        );

        return new GoodsInfoResponseDto(goods);
    }
}
