package main.controller;

import main.requestDTO.StudentRequest;
import main.service.StudentCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:5173") // allows frontend access
public class StudentController {

    @Autowired
    private StudentCommandService studentCommandService;

    // Endpoint to register a student
    @PostMapping("/register")
    public String registerStudent(@RequestBody StudentRequest request) {
        return studentCommandService.registerStudent(request);
    }
}
