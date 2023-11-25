package com.example.hangdang.service;

import com.example.hangdang.dto.CommentRequestDto;
import com.example.hangdang.entity.Comment;
import com.example.hangdang.entity.Goods;
import com.example.hangdang.entity.UserEntity;
import com.example.hangdang.repository.CommentRepository;
import com.example.hangdang.repository.GoodsRepository;
import com.example.hangdang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final GoodsRepository goodsRepository;
    private final UserRepository userRepository;

    public void registerComment(Long goodsId, CommentRequestDto commentRequestdto, UserEntity user) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                () -> new NullPointerException("해당하는 게시글이 존재하지 않습니다.")
        );

        Comment comment = new Comment(goods , commentRequestdto, user);
        commentRepository.save(comment);

    }

    public void deleteComment(Long goodsId, Long commentsId, UserEntity user) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(
                ()-> new NullPointerException("해당하는 상품이 존재하지 않습니다.")
        );
        Comment comment = commentRepository.findById(commentsId).orElseThrow(
                ()-> new NullPointerException("해당하는 댓글이 존재하지 않습니다.")
        );

        user =userRepository.findById(user.getId()).orElseThrow(
                ()-> new NullPointerException("해당하는 유저가 존재하지 않습니다.")
        );

        if(user.getUsername().equals(comment.getUser().getUsername()))
        {
            commentRepository.delete(comment);
        }
    }
}
