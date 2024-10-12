package com.zero.zero_spending.domain.user.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String message;
}
