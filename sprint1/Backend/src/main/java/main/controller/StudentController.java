package main.controller;

import jakarta.validation.Valid;
import main.entity.Student;
import main.repository.StudentRepo;
import main.service.*;
import main.requestDTO.StudentRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentController {

    private final StudentCommandService commandService;
    private final StudentRepo studentRepo;

    @Autowired // Automatically inject command service (DP)
    public StudentController(StudentCommandService commandService, StudentRepo studentRepo) {
        this.commandService = commandService;
        this.studentRepo = studentRepo;
    }


    @PostMapping("/student/register")
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody StudentRequest request) {
        Student created = commandService.registerStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/student/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {
        Student student = studentRepo.findByVerificationToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid verification token."));

        student.setVerified(true);
        student.setVerificationToken(null);
        studentRepo.save(student);

        return ResponseEntity.ok(Map.of("message", "Email verified successfully!"));
    }


}