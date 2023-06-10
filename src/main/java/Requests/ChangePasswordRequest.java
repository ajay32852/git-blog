
package Requests;

import jakarta.validation.constraints.AssertTrue;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString 
public class ChangePasswordRequest {
    
    
    @NotNull(message = "Old Password Is Required")
    @Size(min = 6 ,max = 20)
    private String old_password;
    
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
