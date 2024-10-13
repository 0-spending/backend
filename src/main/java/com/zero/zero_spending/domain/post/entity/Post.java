package com.zero.zero_spending.domain.post.entity;

import com.zero.zero_spending.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Column will still be user_id
    private User user;

    @Column(name = "title", length = 20, nullable = false)
    private String title;

    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "date", length = 255, nullable = false)
    private String date;

    @Column(name = "today_spend")
    private Long todaySpend;

    @Column(name = "latitude", length = 255)
    private String latitude;

    @Column(name = "longitude", length = 255)
    private String longitude;

    @Column(name = "place_name", length = 255)
    private String placeName;

    // Enum for category
    public enum Category {
        SPEND, SAVING, PLACE, ITEM
    }
}
