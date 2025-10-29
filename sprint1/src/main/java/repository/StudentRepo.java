package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
}
