package com.zero.zero_spending.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String nickname;

    @Column(nullable = false, length = 60) // 비밀번호는 암호화되므로 길이를 넉넉히 설정
    private String password;

    @Builder
    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    // Setter 없이 별도의 업데이트 메서드 제공
    public void updateNickName(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}

