package com.zero.zero_spending.domain.user.controller;

import com.zero.zero_spending.domain.user.dto.*;
import com.zero.zero_spending.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    // 로그아웃 API
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("로그아웃 성공. 클라이언트에서 JWT 삭제바람.");
    }

    // 프로필 업데이트 API
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody UpdateProfileRequestDTO request, @AuthenticationPrincipal String currentNickname) {
        userService.updateProfile(request, currentNickname);
        return ResponseEntity.ok("프로필이 성공적으로 업데이트되었습니다.");
    }
}
