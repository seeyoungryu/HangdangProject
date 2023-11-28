package com.example.hangdang.service;

import com.example.hangdang.dto.SignupRequestDto;
import com.example.hangdang.dto.UserInfoResponseDto;
import com.example.hangdang.dto.UsernameRequestDto;
import com.example.hangdang.entity.UserEntity;
import com.example.hangdang.entity.UserRoleEnum;
import com.example.hangdang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 체크할 때 중복 확인 가능

        // 로그인ID 중복 확인
        Optional<UserEntity> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        String nickname = requestDto.getNickname();
        String phoneNumber = requestDto.getPhoneNumber();
        String address = requestDto.getAddress();

        // 사용자 등록
        UserEntity user = new UserEntity(username, password, nickname, phoneNumber, address, role);
        userRepository.save(user);
    }

    public UserInfoResponseDto userInfo(Long userId, UserEntity user) {
        UserEntity user1 = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("해당하는 유저가 없습니다.")
        );

        return new UserInfoResponseDto(user);
    }

    public String checkUsername(UsernameRequestDto requestDto) {
        List<UserEntity> userList = userRepository.findAll();
        boolean isDuplicate = false;

        for(UserEntity user : userList)
        {
            if(user.getUsername().equals(requestDto.getUsername()))
            {
                isDuplicate = true;
            }
        }

        if(isDuplicate)
        {
            return "중복된 닉네임입니다.";
        }
        else
            return "사용가능한 닉네임입니다.";
    }
}