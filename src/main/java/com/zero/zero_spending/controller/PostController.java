package com.zero.zero_spending.controller;

import com.zero.zero_spending.domain.Post;
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

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestPart("post") Post post,
                                           @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String fileUrl = s3Service.uploadFile(file);
                post.setImageUrl(fileUrl); // Assuming your Post entity has an imageUrl field
            }
            Post createdPost = postService.createPost(post);
            return ResponseEntity.ok(createdPost);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null); // Handle error if file upload fails
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id,
                                           @RequestPart("post") Post post,
                                           @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String fileUrl = s3Service.uploadFile(file);
                post.setImageUrl(fileUrl); // Update the image URL in the post entity
            }
            Post updatedPost = postService.updatePost(id, post);
            return ResponseEntity.ok(updatedPost);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null); // Handle error if file upload fails
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
