package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.entity.Course;
import java.util.List;
@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    // Inherits basic CRUD methods like save(), findById(), findAll(), deleteById()

    List<Course> findByStudentNumber(Long studentNumber);
}