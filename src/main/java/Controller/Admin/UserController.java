package Controller.Admin;

import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("admin/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    
 
    @GetMapping
    public String index(Model model,
    @RequestParam(required = false) String keyword,
    @RequestParam(required = false) String status,
    @RequestParam(required = false) String  sort_by
    )
    {
        model.addAttribute("title","Show All User");
        model.addAttribute("show_title","Show all User");
        model.addAttribute("users", userService.getActiveUserList());
        return "backend/user/index";
    }
    
    
    
    
    
    
    
    
    
    
}
