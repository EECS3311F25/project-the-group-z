package main.controller;

import main.dto.StudentDTO;
import main.entity.Student;
import main.service.StudentCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<?> getByUserName(@PathVariable String username) {
        Optional<Student> student = service.getStudentByUsername(username);

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            Map<String, Object> body = new HashMap<>();
            body.put("error", "Not Found");
            body.put("message", "Student with username '" + username + "' not found");
            body.put("errors", new HashMap<>());
            body.put("status", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }


    // Updating info
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student changes) {
        return service.updateStudent(id, changes);
    }
}
