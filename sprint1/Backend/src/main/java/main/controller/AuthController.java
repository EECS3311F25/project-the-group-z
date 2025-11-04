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
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;


@RestController
public class AuthController {

    private final StudentCommandService commandService;
    private final StudentRepo studentRepo;

    @Autowired // Automatically inject command service (DP)
    public AuthController(StudentCommandService commandService, StudentRepo studentRepo) {
        this.commandService = commandService;
        this.studentRepo = studentRepo;
    }


    @PostMapping("/auth/register")
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody StudentRequest request) {
        Student created = commandService.registerStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/auth/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {

        if (commandService.verifyStudent(token)) {
            return ResponseEntity.ok(Map.of("message", "Email verified successfully!"));
        }
        return ResponseEntity.badRequest().build();

    }


}