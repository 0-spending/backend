package com.zero.zero_spending.domain.post.repository;

import com.zero.zero_spending.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
