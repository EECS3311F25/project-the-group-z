package main.service;

import main.entity.Post;
import main.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommandService {

    @Autowired
    private PostRepo postRepo;

    //create a new post
    public Post createPost(Post post) {
        return postRepo.save(post);
    }

    //get all posts
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }
}
