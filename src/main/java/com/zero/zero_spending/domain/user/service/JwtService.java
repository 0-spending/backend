package com.zero.zero_spending.domain.user.service;

import com.zero.zero_spending.domain.user.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtTokenProvider jwtTokenProvider;

    public String generateToken(String nickname) {
        return jwtTokenProvider.generateToken(nickname);
    }

    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    public String getNicknameFromToken(String token) {
        return jwtTokenProvider.getNicknameFromToken(token);
    }
}

