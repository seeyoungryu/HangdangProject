package com.example.hangdang.entity;

import com.example.hangdang.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Comment(Goods goods, CommentRequestDto requestDto, UserEntity user)
    {
        this.goods = goods;
        this.content = requestDto.getContent();
        this.user = user;
    }
}
