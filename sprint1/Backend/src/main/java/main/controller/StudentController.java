package main.controller;

import main.dto.StudentDTO;
import main.entity.Student;
import main.service.StudentCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    private final StudentCommandService service;

    public StudentController(StudentCommandService service) {
        this.service = service;
    }

    // Data Transfer Over/summary endpoint 
    @GetMapping("/{id}/sidebar")
    public StudentDTO sidebar(@PathVariable Long id) {
        return service.toSidebar(id);
    }

    // Full student/profile 
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return service.getStudentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    // Delete a student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // List all students
    @GetMapping
    public List<Student> all() {
        return service.getAllStudents();
    }

    // Finding user via username
    @GetMapping("/by-username/{username}")
    public Student getByUserName(@PathVariable String username) {
        return service.getStudentByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    // Updating info
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student changes) {
        return service.updateStudent(id, changes);
    }
}
