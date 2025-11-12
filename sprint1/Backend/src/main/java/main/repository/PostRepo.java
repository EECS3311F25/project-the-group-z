package main.repository;

import main.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    // Fetch all posts ordered by timestamp (newest first)
    List<Post> findAllByOrderByTimestampDesc();

    // Fetch posts for a specific user ordered by newest first
    List<Post> findByUsernameOrderByTimestampDesc(String username);
}
