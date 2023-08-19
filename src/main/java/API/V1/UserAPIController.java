
package API.V1;

import Entity.User;
import ExceptionHandler.ApiResponse;
import Requests.ChangePasswordRequest;
import Requests.ProfileRequest;
import Service.UserService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api/v1/users/")
public class UserAPIController {
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping("profile-detail/{id}")
    public ResponseEntity<ApiResponse <User>> getProfileDetail(@PathVariable("id") int id)
    {
         //ApiResponse<User> response = new ApiResponse<>();  
         Optional<User> optionalUser = userService.getUserById(id);
         if (optionalUser.isEmpty()) {
            // User with the provided ID not found
            ApiResponse<User> response = new ApiResponse<>();
            response.setStatus(false);
            response.setMessage("User not found");
            response.setResponseCode("404 Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        User user = optionalUser.get();
        ApiResponse<User> response = new ApiResponse<>();
        response.setStatus(true);
        response.setMessage("User found");
        response.setResponseCode("200 OK");
        response.setData(user);
        return ResponseEntity.ok(response);     
    }
    
    @PostMapping("edit-profile")
    public ResponseEntity<ApiResponse <String>> editProfile(@Valid @RequestBody ProfileRequest profileRequest,BindingResult result)
    {
        
        if (result.hasErrors()) {
            // Validation errors occurred
            ApiResponse<String> response = new ApiResponse<>();
            response.setStatus(false);
            response.setMessage("Validation failed");
            response.setResponseCode("400 Bad Request");
            response.setErrors(getAllValidationErrors(result));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } 
        
        
        
        Optional<User> authenticatedUser = getAuthenticatedUser();
        // Check if firstName and/or lastName fields are provided and update accordingly
        if (profileRequest.getFirst_name() != null) {
           // authenticatedUser.setFirstName (profileRequest.getFirst_name());
        }
        
        if (profileRequest.getLast_name() != null) {
           // authenticatedUser.setLastName(profileRequest.getLastName());
        }
        
        if(profileRequest.getEmail()!=null)
        {
            //
        }

        // Save the updated user
       // User updatedUser = userRepository.save(authenticatedUser);

        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(true);
        response.setMessage("Profile updated successfully");
        response.setResponseCode("200 OK");
        response.setData("Profile updated");
        return ResponseEntity.ok(response);
        
        
    }
    
    // Helper method to retrieve the authenticated user
    private Optional<User> getAuthenticatedUser() {
        // Implement the logic to retrieve the authenticated user from the security context or session
        // Return the authenticated user object
        int id=4;
        Optional<User> optionalUser = userService.getUserById(id); 
        return optionalUser;
    }
    
    //  Validation Exception Handler   
     private List<String> getAllValidationErrors(BindingResult result)
    {
        List<String> errors=new ArrayList<>();
        for(FieldError error :result.getFieldErrors())
        {
           errors.add(error.getDefaultMessage());
        }
        return errors;
    } 
   
  @GetMapping("list")   
   public ResponseEntity<ApiResponse <List<User>>> getAllUseList()
   {
       ApiResponse< List<User>> response=new ApiResponse<>();
       List<User> userlist =userService.getActiveUserList();
       response.setStatus(true);
       response.setMessage("Successfully fetched all active user list");
       response.setResponseCode("200 OK");
       response.setData(userlist);
       return ResponseEntity.ok(response);
       
   }
   
   @PostMapping("change-password")
   public ResponseEntity<ApiResponse<String>> changePassword(@Valid @RequestBody ChangePasswordRequest changePassword,
           BindingResult result
           )
   {
       ApiResponse<String> response=new ApiResponse<>();   
       
     if(result.hasErrors())
     {
        response.setStatus(false);
        response.setMessage("Validation failed");
        response.setResponseCode("400 Bad Request");
        response.setErrors(getAllValidationErrors(result));
        return ResponseEntity.badRequest().body(response);   
     }
    
      response.setStatus(true);
      response.setMessage("Password has changed successfully");
      response.setResponseCode("200 OK");
      return ResponseEntity.ok(response); 
   }
    
    
}
