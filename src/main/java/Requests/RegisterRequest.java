
package Requests;

import ExceptionHandler.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    
    @NotBlank(message = "First name is required")
    private String first_name;

    @NotBlank(message = "Last name is required")
    private String last_name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @UniqueEmail
    private String email;  
    
    
    @NotNull(message = "Password is Required")
    @Size(min = 6, max = 20)
    private String password;
    
    @NotNull(message = "Confirm Password is Required")
    @Size(min = 6, max = 20)
    private String confirm_password;
    
    @AssertTrue(message = "Password and Confirm Password must match")
    private boolean isPasswordMatching() {
        return password.equals(confirm_password);
    }
    
    
    
    
    
}
