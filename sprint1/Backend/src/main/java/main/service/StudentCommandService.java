package main.service;

import main.dto.StudentDTO;
import main.entity.Student;
import main.repository.StudentRepo;
import main.repository.CourseRepo;
import main.repository.CourseSessionRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCommandService {

    private final StudentRepo repo;

    public StudentCommandService(StudentRepo repo) {
        this.repo = repo;
    }

    // Convert to sidebar DTO (minimal data)
    public StudentDTO toSidebar(Long id) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        return new StudentDTO(
                s.getStudentNumber(),
                s.getFirstName(),
                s.getLastName(),
                s.getUsername(),
                s.getEmail(),
                s.getMajor()
        );
    }

    // Retrieve student by ID
    public Optional<Student> getStudentById(Long id) {
        return repo.findById(id);
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // Create a student
    public Student createStudent(Student s) {
        return repo.save(s);
    }

    // Delete a student
    public void deleteStudent(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        repo.deleteById(id);
    }

    // Find by username
    public Optional<Student> getStudentByUsername(String username) {
        return repo.findByUsername(username);
    }

    // Update student fields (partial update)
    public Student updateStudent(Long id, Student changes) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        if (changes.getUsername() != null) s.setUsername(changes.getUsername());
        if (changes.getFirstName() != null) s.setFirstName(changes.getFirstName());
        if (changes.getLastName() != null) s.setLastName(changes.getLastName());
        if (changes.getEmail() != null) s.setEmail(changes.getEmail());
        if (changes.getMajor() != null) s.setMajor(changes.getMajor());
        if (changes.getBio() != null) s.setBio(changes.getBio());

        return repo.save(s);
    }

    //TO REVIEW/FIX
    // This method processes an uploaded schedule image for a student.
    // It extracts text using OCR, parses it into Course and CourseSession objects,
    // deduplicates existing courses, saves new ones, and returns a summary result.
//
//    public ScheduleProcessingResult processScheduleImage(Long studentId, MultipartFile imageFile) {
//
//        // 1. Retrieve the student from the database
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
//
//        // TO IMPLEMENT: 2. Extract text from the uploaded image using OCR
//        String extractedText = ocrService.extractText(imageFile);
//
//        // TO IMPLEMENT: 3. Parse the extracted text into structured Course objects
//        List<Course> parsedCourses = scheduleParser.parseCourses(extractedText);
//
//        // 4. Initialize counters and flags
//        int coursesAdded = 0;
//        int sessionsAdded = 0;
//        boolean newCoursesCreated = false;
//
//        // 5. Iterate through parsed courses
//        for (Course course : parsedCourses) {
//
//            // Replace this with your deduplication logic:
//            // If courses are uniquely identified by courseCode + section, use:
//            Optional<Course> existingCourse = courseRepository.findByCourseCodeAndSection(
//                    course.getCourseCode(), course.getSection()
//            );
//
//            // 6. Save new course if not found
//            Course courseToUse = existingCourse.orElseGet(() -> {
//                newCoursesCreated = true;
//                coursesAdded++;
//                return courseRepository.save(course);
//            });
//
//            // 7. Associate the course with the student
//            student.getCourses().add(courseToUse);
//
//            // 8. Save each session under the course
//            for (CourseSession session : course.getSessions()) {
//                session.setCourse(courseToUse); // link session to course
//                courseSessionRepository.save(session); // persist session
//                sessionsAdded++;
//            }
//        }
//
//        // 9. Save the updated student with new course associations
//        studentRepository.save(student);
//
//        // 10. Return a result object summarizing what was added
//        return new ScheduleProcessingResult(newCoursesCreated, coursesAdded, sessionsAdded);
//    }

}
