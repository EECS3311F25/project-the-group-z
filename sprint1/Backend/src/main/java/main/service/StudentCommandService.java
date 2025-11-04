package main.service;

import main.entity.Student;
import main.repository.StudentRepo;
import main.requestDTO.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentCommandService {

    private final StudentRepo studentRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public StudentCommandService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String registerStudent(StudentRequest request) {
        Student student = new Student();
        student.setUsername(request.getUsername());
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());

        studentRepo.save(student);
        return "Student registered successfully!";
    }
}


