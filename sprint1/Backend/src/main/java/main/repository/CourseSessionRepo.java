package main.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import main.entity.CourseSession;

public interface CourseSessionRepo extends JpaRepository<CourseSession, Long> {
    // Add queries here if needed later
}