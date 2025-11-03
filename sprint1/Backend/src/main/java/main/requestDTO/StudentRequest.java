package main.requestDTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data // Automatically adds getters, setters, toString, equals, hashCode
@NoArgsConstructor // Adds a no-argument constructor (required by JPA)
@AllArgsConstructor //  Adds a constructor with all fields // Automatically create arguments constructor.

public class StudentRequest {
    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Email is required.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@my\\.yorku\\.ca$",
            message = "Email must end with @my.yorku.ca."
    ) // Must be a valid YorkU email.
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters.")
    private String password;

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;


}