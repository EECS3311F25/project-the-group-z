package main.controller;

import main.entity.Post;
import main.service.PostCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {

    @Autowired
    private PostCommandService postService;

    // create post
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Post created successfully");
        response.put("id", newPost.getId());

        return ResponseEntity.ok(response);
    }

    // view all posts (newest first)
    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/{id}/like")
    public ResponseEntity<Post> toggleLike(@PathVariable Long id, @RequestParam String username) {
        Post updated = postService.toggleLike(id, username);
        return ResponseEntity.ok(updated);
    }

}
