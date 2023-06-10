
package API.V1;

import Entity.User;
import ExceptionHandler.ApiResponse;
import Requests.LoginRequest;
import Service.UserService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginAPIController {
  
    @Autowired
    private UserService userService;
    
    
  
  @PostMapping("/login")
  public ResponseEntity<ApiResponse<User>> login(@Valid @RequestBody LoginRequest loginrequest,
  BindingResult result
  )
  {
     ApiResponse<User> response = new ApiResponse<>();
       if (result.hasErrors()) {
            // Validation errors occurred
            response.setStatus(false);
            response.setMessage("Validation failed");
            response.setResponseCode("400 Bad Request");
            response.setErrors(getAllValidationErrors(result));
            return ResponseEntity.badRequest().body(response);
        }   
       
       if (authenticateUser(loginrequest.getEmail(), loginrequest.getPassword())) {
            response.setStatus(true);
            response.setMessage("User login successfully");
            response.setResponseCode("200 OK");
            response.setData(userService.findByEmailUser(loginrequest.getEmail()));
            
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(false);
            response.setMessage("Invalid email or password");
            response.setResponseCode("401 Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
       
   
      
  }
  
   private List<String> getAllValidationErrors(BindingResult result)
    {
        List<String> errors=new ArrayList<>();
        for(FieldError error :result.getFieldErrors())
        {
           errors.add(error.getDefaultMessage());
        }
        return errors;
    } 
   
   
    private boolean authenticateUser(String email, String password) {
        // Perform authentication logic here
        // You can check against a database or any other authentication mechanism
        // Return true if the email and password match, otherwise false
        // This is just a placeholder implementation
        return userService.authenticateUserCheckUser(email,password);
       // return email.equals("user@example.com") && password.equals("password");
    }
    
    
    
}
