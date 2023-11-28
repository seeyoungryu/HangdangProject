package com.example.hangdang.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "goods_like")  // 테이블 이름 변경
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    // 생성자 추가
    public Like(Goods goods, UserEntity user) {
        this.goods = goods;
        this.user = user;
    }
}
