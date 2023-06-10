
package Controller.Auth;

import Entity.User;
import Requests.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String index()
    {
        return "auth/login";
    }
    
    @PostMapping("/doLogin")
    public String doLogin(
     @Valid @ModelAttribute("user")  LoginRequest loginRequest,BindingResult result,
     RedirectAttributes redirectAttributes,Model model)
    {
      
      //System.out.println("LoginData123"+loginRequest.getEmail()+"pass"+loginRequest.getPassword());
      if(result.hasErrors())
       {
           System.out.println("ErrorCount123"+result.getErrorCount());
           System.out.println("ErrorName12"+result.hasErrors());
           model.addAttribute("user",loginRequest);
           return"auth/login";
       }  
        
        
       return "auth/login";   
    }
    
    
    
    
    
    
}
