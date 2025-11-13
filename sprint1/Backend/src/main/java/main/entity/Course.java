package main.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course",
        uniqueConstraints = @UniqueConstraint(columnNames = {"courseCode", "section"}))
@Data // Automatically adds getters, setters, toString, equals, hashCode
@NoArgsConstructor // Adds a no-argument constructor (required by JPA)
@AllArgsConstructor // Adds a constructor with all fields
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    String courseName;
    @Column(nullable = false)  // Use courseCode as part of composite key
    String courseCode;
    @Column(nullable = false)  // Use section as part of composite key
    char courseSection;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSession> sessions = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
