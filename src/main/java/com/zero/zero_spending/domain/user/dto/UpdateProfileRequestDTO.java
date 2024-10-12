package com.zero.zero_spending.domain.user.dto;

import lombok.Data;

@Data
public class UpdateProfileRequestDTO {
    private String nickname;
    private String password;
}
