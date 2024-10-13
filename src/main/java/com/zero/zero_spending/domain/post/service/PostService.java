package com.zero.zero_spending.domain.post.service;

import com.zero.zero_spending.domain.post.entity.Post;
import com.zero.zero_spending.domain.user.entity.User;
import com.zero.zero_spending.domain.user.repository.UserRepository;
import com.zero.zero_spending.domain.post.dto.PostDTO;
import com.zero.zero_spending.domain.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zero.zero_spending.domain.post.entity.Image;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;  // Assuming you have a UserRepository to find users

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post createPost(PostDTO postDTO, List<String> imageUrls) {
        // Find the user by userId
        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create the post entity
        Post post = new Post();
        post.setUsers(user);
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCategory(postDTO.getCategory());
        post.setDate(postDTO.getDate());
        post.setTodaySpend(postDTO.getTodaySpend());
        post.setLatitude(postDTO.getLatitude());
        post.setLongitude(postDTO.getLongitude());
        post.setPlaceName(postDTO.getPlaceName());

        List<Image> images = new ArrayList<>();
        for (String url : imageUrls) {
            Image image = new Image();
            image.setImageUrl(url);
            image.setPost(post);
            images.add(image);
        }
        post.setImages(images);

        return postRepository.save(post);
    }

    public Post updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCategory(postDTO.getCategory());
        post.setDate(postDTO.getDate());
        post.setTodaySpend(postDTO.getTodaySpend());
        post.setLatitude(postDTO.getLatitude());
        post.setLongitude(postDTO.getLongitude());
        post.setPlaceName(postDTO.getPlaceName());

        return postRepository.save(post);  // Save updated post to DB
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
