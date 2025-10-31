package main.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.LinkedList;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentNumber;


}
