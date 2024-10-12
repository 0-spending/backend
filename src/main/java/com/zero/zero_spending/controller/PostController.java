package com.zero.zero_spending.controller;

import com.zero.zero_spending.domain.Post;
import com.zero.zero_spending.dto.PostDTO;
import com.zero.zero_spending.s3.S3Service;
import com.zero.zero_spending.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private S3Service s3Service;

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);  // Fetch post by ID
    }

    @PostMapping
    public Post createPost(@RequestPart(value = "post") PostDTO postDTO,
                           @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        String imageUrl = null;

        if (file != null) {
            imageUrl = s3Service.uploadFile(file);  // Upload image to S3 and get URL
        }

        return postService.createPost(postDTO, imageUrl);  // Pass DTO and image URL to service
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        return postService.updatePost(id, postDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
