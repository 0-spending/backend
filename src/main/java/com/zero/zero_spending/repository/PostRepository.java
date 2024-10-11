package com.zero.zero_spending.repository;

import com.zero.zero_spending.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
