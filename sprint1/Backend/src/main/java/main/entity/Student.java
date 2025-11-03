package main.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "student")
@Data // Automatically adds getters, setters, toString, equals, hashCode
@NoArgsConstructor // Adds a no-argument constructor (required by JPA)
@AllArgsConstructor //  Adds a constructor with all fields // Automatically create arguments constructor.
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentNumber;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String major;

    private String bio;

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
        return username;
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
    
    public void setUserName(String username){
        this.username = username;
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
