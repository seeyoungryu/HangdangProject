package com.example.hangdang.entity;

import com.example.hangdang.dto.CommentRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private UserEntity user;

    public Comment(Goods goods, CommentRequestDto requestDto, UserEntity user)
    {
        this.goods = goods;
        this.content = requestDto.getContent();
        this.user = user;
    }
}
