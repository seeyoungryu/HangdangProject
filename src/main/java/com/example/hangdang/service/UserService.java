package com.example.hangdang.service;

import com.example.hangdang.dto.SignupRequestDto;
import com.example.hangdang.dto.UserInfoResponseDto;
import com.example.hangdang.entity.UserEntity;
import com.example.hangdang.entity.UserRoleEnum;
import com.example.hangdang.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // ADMIN_TOKEN

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());


        // 로그인ID 중복 확인
        Optional<UserEntity> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "중복된 사용자가 존재합니다."
            );
        }


        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        String nickname = requestDto.getNickname();
        String phoneNumber = requestDto.getPhoneNumber();
        String address = requestDto.getAddress();

        // 사용자 등록
        UserEntity user = new UserEntity(username, password, nickname, phoneNumber, address, role);
        userRepository.save(user);
    }

//    public UserInfoResponseDto userInfo(Long userId, UserEntity user) {
//        UserEntity user = userRepository.findById(userId).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 유저가 없습니다.")
//        );
//
//        return new UserInfoResponseDto(user);
//    }


    public UserInfoResponseDto userInfo(Long userId) {
        UserEntity foundUser = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 유저가 없습니다.")
        );

        return new UserInfoResponseDto(foundUser);
    }

    public ResponseEntity<String> checkUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        if (userEntity.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "중복된 username 입니다."
            );
        } else {
            return ResponseEntity.ok("사용가능한 username 입니다.");
        }
    }

}


