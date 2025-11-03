package main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.entity.Student;
import main.repository.StudentRepo;

@SpringBootApplication
@RestController
public class StudentController {


    @GetMapping("/student/Register")
    public String hello(@RequestParam StudentRequest request) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/test-student")
    public Student testStudent(StudentRepo repo) {
        return repo.findAll().stream().findFirst().orElse(null);
    }
}
