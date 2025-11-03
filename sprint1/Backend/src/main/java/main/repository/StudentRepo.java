package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import main.entity.Student;
import java.util.Optional;




public interface StudentRepo extends JpaRepository<Student, Long> {

    Student findByStudentNumber(Long studentNumber);
    Optional<Student> findByUserName(String userName);
}



