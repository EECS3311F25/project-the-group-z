package main.services;

import main.dto.StudentDTO;
import main.entity.Student;
import main.repository.StudentRepo;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepo repo;

    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    public StudentDTO toSidebar(Long id) {
        Student s = repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found: " + id));
        return new StudentDTO(s.getStudentNumber(), s.getFirstName(), s.getLastName(), s.getUserName(), s.getEmail(), s.getMajor());
    }

    public Student createOrUpdate(Student student) {
        return repo.save(student);
    }
}
