package com.example.hangdang.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String username;
    private String password;
    private String nickname;  // 추가된 필드

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    // nickname에 대한 getter 메소드 추가
    public String getNickname() {
        return nickname;
    }
}
