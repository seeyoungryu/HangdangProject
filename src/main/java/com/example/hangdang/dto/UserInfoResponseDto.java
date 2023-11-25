package com.example.hangdang.dto;

import com.example.hangdang.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private String username;
    private String nickname;
    private String phoneNumber;
    private String address;

    public UserInfoResponseDto(UserEntity user)
    {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
    }
}
