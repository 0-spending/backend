package com.zero.zero_spending.domain.user.service;

import com.zero.zero_spending.domain.user.dto.LoginRequestDTO;
import com.zero.zero_spending.domain.user.dto.SignupRequestDTO;
import com.zero.zero_spending.domain.user.dto.SignupResponseDTO;
import com.zero.zero_spending.domain.user.dto.UpdateProfileRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    SignupResponseDTO registerUser(SignupRequestDTO request);
    String loginUser(LoginRequestDTO request);
    void updateProfile(UpdateProfileRequestDTO request, String currentNickname);
}
