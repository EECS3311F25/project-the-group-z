package main.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.LinkedList;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_number") // maps to DB column
    private Long studentNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "major")
    private String major;

    @Column(name = "bio")
    private String bio;

    public Student(){}

    // Getters
    public Long getStudentNumber(){
        return studentNumber;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getUserName(){
        return userName;
    }

    public String getEmail(){
        return email;
    }

    public String getMajor(){
        return major;
    }

    public String getBio(){
        return bio;
    }

    // Setters
    public void setStudentNumber(Long studentNumber){
        this.studentNumber = studentNumber;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setMajor(String major){
        this.major = major;;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

}
