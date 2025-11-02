package main.controller;

import main.entity.Student;
import main.service.*;
import main.requestDTO.StudentRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {

    private final StudentCommandService commandService;

    @Autowired // Automatically inject command service (DP)
    public StudentController(StudentCommandService commandService) {
        this.commandService = commandService;
    }


    @PostMapping("/student/Register")
    public ResponseEntity<Student> registerStudent(@RequestBody StudentRequest request) {
        Student created = commandService.registerStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


}