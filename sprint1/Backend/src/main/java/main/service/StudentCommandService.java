package main.service;

import org.springframework.beans.factory.annotation.Autowired;
import main.requestDTO.StudentRequest;
import main.entity.Student;
import main.repository.StudentRepo;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentCommandService {
    private final StudentRepo studentRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentCommandService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Student registerStudent(StudentRequest request) {

        // If username already exists.
        if (studentRepo.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken.");
        }

        // If email already exists.
        if (studentRepo.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered.");
        }

        Student student = new Student();
        student.setUsername(request.getUsername());
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());

        return studentRepo.save(student);
    }
}