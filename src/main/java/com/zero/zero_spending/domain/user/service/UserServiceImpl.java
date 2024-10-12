package com.zero.zero_spending.domain.user.service;

import com.zero.zero_spending.domain.user.dto.*;
import com.zero.zero_spending.domain.user.entity.User;
import com.zero.zero_spending.domain.user.repository.UserRepository;
import com.zero.zero_spending.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignupResponseDTO registerUser(SignupRequestDTO request) {
        // 닉네임 중복 체크
        if (userRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        // 비밀번호 암호화 및 저장
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.builder()
                .nickname(request.getNickname())
                .password(encodedPassword)
                .build();

        userRepository.save(user);

        // 회원가입 성공 메시지 반환
        SignupResponseDTO response = new SignupResponseDTO();
        response.setMessage("회원가입 성공");
        return response;
    }

    @Override
    public String loginUser(LoginRequestDTO request) {
        User user = userRepository.findByNickname(request.getNickname())
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return "Authenticated"; // JWT 토큰 발급은 JwtService에서 처리
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getNickname(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
