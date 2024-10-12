package com.zero.zero_spending.dto;

import com.zero.zero_spending.domain.Post.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Long userId;     // Client sends only userId
    private String title;
    private String content;
    private Category category;
    private String date;
    private Long todaySpend;
    private String latitude;
    private String longitude;
    private String placeName;
}