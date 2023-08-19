package API.V1;

import Entity.User;
import ExceptionHandler.ApiResponse;
import Requests.RegisterRequest;
import Service.UserService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api/v1")
public class RegisterAPIController {
    
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult) 
    {
        ApiResponse<String> response = new ApiResponse<>();

        if (bindingResult.hasErrors()) {
            // Validation errors occurred
            response.setStatus(false);
            response.setMessage("Validation failed");
            response.setResponseCode("400 Bad Request");
            response.setErrors(getAllValidationErrors(bindingResult));
            return ResponseEntity.badRequest().body(response);
            
        }
        // Validation successful, proceed with user registration
        User saveUser=userService.registerUser(registerRequest);
        response.setStatus(true);
        response.setMessage("User registered successfully");
        response.setResponseCode("200 OK");
        response.setData(null);
        return ResponseEntity.ok(response);
        
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
    
    
   
    
    
}
