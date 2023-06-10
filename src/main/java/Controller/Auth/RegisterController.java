
package Controller.Auth;
import Entity.User;
import Repository.UserRepository;
import Service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private EmailService emailService;
    
    
    @GetMapping("/register")
    public String index(Model model)
    {
        
        model.addAttribute("user",new User());
        return "auth/register";
    }
    @PostMapping("/register-user")
    public String SubmitRegisterForm(@Valid @ModelAttribute("user") User user,BindingResult result,
            RedirectAttributes redirectAttributes,Model model)
    {
       if(result.hasErrors())
       {
           System.out.println(result.getErrorCount());
           model.addAttribute("user",user);
           return"auth/register";
       }
        userRepo.save(user);
        String Message= "Hi "+user.getFirst_name()+" "+user.getLast_name()+" <br/> Your Password <br>"+user.getPassword();
        emailService.SendRegisterUserMail(user.getEmail(),"User Register",Message);
        redirectAttributes.addFlashAttribute("successMessage", "You have successfully register");
        return "redirect:/register"; 
    }
    
    
    
    
    
    
}
