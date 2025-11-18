package main.service;

import main.entity.Post;
import main.entity.Like;
import main.entity.Comment;
import main.repository.PostRepo;
import main.repository.LikeRepo;
import main.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostCommandService {

    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    private LikeRepo LikeRepo;

    @Autowired
    private CommentRepo commentRepo;

  
    public Post createPost(Post post) {
        return postRepo.save(post);
    }

    public List<Post> getAllPosts() {
        // newest first
        return postRepo.findAll().stream()
                .sorted((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()))
                .toList();
    }

    //Like a post
    public Post toggleLike(Long postId, String username) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Check if user already liked
        Optional<Like> existing = LikeRepo.findByPostAndUsername(post, username);

        if (existing.isPresent()) {
            // Unlike
            LikeRepo.delete(existing.get());
            post.setLikes(post.getLikes() - 1);
        } else {
            // Like
            Like like = new Like();
            like.setPost(post);
            like.setUsername(username);
            LikeRepo.save(like);
            post.setLikes(post.getLikes() + 1);
        }

        return postRepo.save(post);
    }

    //Add a comment to a post
    public Comment addComment(Long postId, Comment comment) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);
        return commentRepo.save(comment);
    }
}
