
package Controller.Admin;

import Service.BannerService;
import Service.CategoryService;
import Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class DashboardController {
    
    @Autowired
    private BannerService bannerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    
    
    
    @GetMapping("/dashboard")
    public String index(Model model)
    {
        System.out.println("banner1231"+bannerService.getBannerCount());
        model.addAttribute("bannerCount",bannerService.getBannerCount());
        model.addAttribute("countCategory",categoryService.getCategoryCount());
        model.addAttribute("countPost",postService.getPostCount() );
        model.addAttribute("title", "Dashboard");
        return "backend/dashboard";
    }  
    
    
    
    
    
    
    
    
}
