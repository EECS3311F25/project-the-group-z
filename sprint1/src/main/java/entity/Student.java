package entity;

import java.util.List;
import java.util.LinkedList;

import jakarta.persistence.*;

@Entity
public class Student {
    String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String studentNumber;

    String major;
    List<Course> courses = new LinkedList<>();
    int year;
}

