package main.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.DayOfWeek;
import java.time.LocalTime;
@Entity
@Table(name = "course_session")
@Data // Automatically adds getters, setters, toString, equals, hashCode
@NoArgsConstructor // Adds a no-argument constructor (required by JPA)
@AllArgsConstructor // Adds a constructor with all fields
public class CourseSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cSessionId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;
}