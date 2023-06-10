package Service;

import Entity.User;
import Repository.UserRepository;
import Requests.RegisterRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
//    public List<User> getUserByIsActive(int isActive)
//    {
//      return   userRepo.findByIsActive(isActive);
//    }
//    
    
    public User registerUser(RegisterRequest registrationRequest)
    {
        User user = new User(); 
        user.setFirst_name(registrationRequest.getFirst_name());
        user.setLast_name(registrationRequest.getLast_name());
        user.setEmail(registrationRequest.getEmail());
        user.setConfirm_password(registrationRequest.getConfirm_password());
        user.setPassword(registrationRequest.getPassword());
        return userRepo.save(user);
    }
    public boolean authenticateUserCheckUser(String email, String password)
    {
        User user = userRepo.findByEmail(email);
        if(user==null)
        {
           return false; 
        }
        if (!user.getPassword().equals(password)) {
           return false; 
        }
        if(email.equals(user.getEmail()) && password.equals(user.getPassword()))
        {
            return true;
        }
        return false;  
        
    }
    // get user data By Email    
    public User findByEmailUser(String email)
    {
      return userRepo.findByEmail(email);
    }
    
    public Optional<User> getUserById(int user_id)
    {
       return userRepo.findById(user_id);
    }
    
    public List<User> getActiveUserList()
    {
       //return  userRepo.findAll();
       return  userRepo.findAllActiveUsers(); 
       // return  userRepo.findAdminDetail(); 
    }
    
    
    
    
    
    
}
