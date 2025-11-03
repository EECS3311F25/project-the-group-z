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
    private final EmailService emailService;

    @Autowired
    public StudentCommandService(StudentRepo studentRepo, EmailService emailService) {
        this.studentRepo = studentRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.emailService = emailService;
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

        String link = "http://localhost:8080/student/verify?token=" + student.getVerificationToken();
        emailService.sendEmail(
          student.getEmail(),
          "Verify your YUCircle account",
          "Click to verify your account:" + link
        );

        return studentRepo.save(student);
    }
}