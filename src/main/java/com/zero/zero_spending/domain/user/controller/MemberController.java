package com.zero.zero_spending.domain.user.controller;

import com.zero.zero_spending.domain.user.dto.LoginRequestDTO;
import com.zero.zero_spending.domain.user.dto.LoginResponseDTO;
import com.zero.zero_spending.domain.user.dto.SignupRequestDTO;
import com.zero.zero_spending.domain.user.dto.SignupResponseDTO;
import com.zero.zero_spending.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody SignupRequestDTO request) {
        // 서비스에서 회원가입 처리
        SignupResponseDTO response = userService.registerUser(request);

        // 회원가입 성공 메시지 반환
        return ResponseEntity.ok(response);
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        // 로그인 처리 후 JWT 토큰 반환
        String token = userService.loginUser(request);

        // JWT 토큰과 메시지를 담아 응답
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setMessage("로그인 성공");

        return ResponseEntity.ok(response);
    }
}
