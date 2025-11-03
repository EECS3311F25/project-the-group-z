package main.controller;

import main.dto.StudentDTO; 
import main.entity.Student;
import main.repository.StudentRepo;
import main.services.StudentService; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    private final StudentService service;
    private final StudentRepo repo;

    public StudentController(StudentService service, StudentRepo repo) {
        this.service = service;
        this.repo = repo;
    }

    // Data Transfer Over/summary endpoint 
    @GetMapping("/{id}/sidebar")
    public StudentDTO sidebar(@PathVariable Long id) {
        return service.toSidebar(id);
    }

    

    // Full student/profile 
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }
    
    // Delete a student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        repo.deleteById(id);
    return ResponseEntity.noContent().build(); 
}

    // List all students
    @GetMapping
    public List<Student> all() {
        return repo.findAll();
    }

    // Create student
    @PostMapping
    public Student create(@RequestBody Student s) {
        return repo.save(s);
    }

    // finding User via username
    @GetMapping("/by-username/{username}")
    public Student getByUserName(@PathVariable String username) {
        return repo.findByUserName(username)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
}

    // Updating info
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student changes) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        if (changes.getUserName() != null) s.setUserName(changes.getUserName());
        if (changes.getFirstName() != null) s.setFirstName(changes.getFirstName());
        if (changes.getLastName() != null) s.setLastName(changes.getLastName());
        if (changes.getEmail() != null) s.setEmail(changes.getEmail());
        if (changes.getMajor() != null) s.setMajor(changes.getMajor());
        if (changes.getBio() != null) s.setBio(changes.getBio());


        return repo.save(s);
    }
}
