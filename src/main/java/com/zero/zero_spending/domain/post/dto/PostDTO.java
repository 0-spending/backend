package com.zero.zero_spending.domain.post.dto;

import com.zero.zero_spending.domain.post.entity.Post.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDTO {
    private Long userId;
    private String title;
    private String content;
    private Category category;
    private String date;
    private Long todaySpend;
    private String latitude;
    private String longitude;
    private String placeName;
    private List<String> imageUrls;
}