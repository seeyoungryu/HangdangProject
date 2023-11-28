package com.example.hangdang.controller;

import com.example.hangdang.dto.SignupRequestDto;
import com.example.hangdang.dto.UserInfoResponseDto;
import com.example.hangdang.dto.UsernameRequestDto;
import com.example.hangdang.security.UserDetailsImpl;
import com.example.hangdang.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/login-page")
//    public String loginPage() {
//        return "login";
//    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);

        return "redirect:/api/login-page";
    }

    //    @GetMapping("/signup/check-name")
//    public String checkUsername(UsernameRequestDto requestDto) {
//        return userService.checkUsername(requestDto);
//    }
    @GetMapping("/check-name")
    public ResponseEntity<String> checkUsername(@RequestBody UsernameRequestDto requestDto) {
        return userService.checkUsername(requestDto);
    }

    @GetMapping("/userinfo/{userId}")
    public UserInfoResponseDto userInfo(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.userInfo(userId, userDetails.getUser());
    }
}