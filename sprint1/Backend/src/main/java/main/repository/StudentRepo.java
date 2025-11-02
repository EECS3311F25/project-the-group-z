package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import main.entity.Student;


public interface StudentRepo extends JpaRepository<Student, Long> {

    Student findByStudentNumber(Long studentNumber);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
