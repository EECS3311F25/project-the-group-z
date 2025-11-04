package main.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {
    String courseName;
    String courseCode;
    String courseType;  // E.g. lecture, tutorial, lab
    String day;  // Monday, Tuesday, etc.
    String time;  // E.g. 10:30-12:00
    String room;  // E.g. LAS A
    char courseSection;

    @ManyToMany
    private List<Student> students;
}
