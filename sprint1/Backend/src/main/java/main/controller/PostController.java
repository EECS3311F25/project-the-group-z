package main.controller;

import main.entity.Post;
import main.service.PostCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:5173") //allows frontend access
public class PostController {

    @Autowired
    private PostCommandService postService;

    //create a new post
    @PostMapping("/create")
    public String createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        return "Post created successfully with ID: " + newPost.getId();
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
