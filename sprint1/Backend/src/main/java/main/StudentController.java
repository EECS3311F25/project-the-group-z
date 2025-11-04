package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentController {

    @Autowired
    private StudentCommandService studentCommandService;

    @GetMapping("/student/Register")
    public String hello(@RequestParam StudentRequest request) {
        return String.format("Hello, %s!", name);
    }

    @PostMapping("/upload-schedule")
    public ResponseEntity<String> uploadSchedule(@RequestParam("studentNumber") Long studentNumber, @RequestParam("image") MultipartFile image) {
        studentCommandService.processScheduleImage(studentNumber, image);
        return ResponseEntity.ok("Schedule processed and saved.");
    }


}