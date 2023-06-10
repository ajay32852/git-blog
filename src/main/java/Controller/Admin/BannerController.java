
package Controller.Admin;
import Entity.Banner;
import Repository.BannerRepository;
import Service.BannerService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.lang.String;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("admin")
public class BannerController {
    
    @Autowired 
    private BannerRepository bannerRepository;
    @Autowired
    private BannerService bannerService;
    
    
    private String userDirectory = "src/main/resources/static/uploads/";
    
    
    @GetMapping("/banner")
    public String index(Model model)
    {
        System.out.println("hello ajay");
        System.out.println(bannerService.getBannersByIsActive(1));
        model.addAttribute("title","Banner Manager");
        model.addAttribute("show_title","Show all Banner");
        model.addAttribute("banners",bannerService.getBannersByIsActive(1));
        return "backend/banner/index";
    }
    
    //@GetMapping("banner/create")
    @GetMapping("/bcreate")
    public String create(Model model,Banner banner)
    {
      model.addAttribute("banner",banner);
      model.addAttribute("title","Banner Manager");
      model.addAttribute("show_title","Add Banner");
      return "backend/banner/createOrUpdate";   
    }
    
        
  
    @PostMapping("/bstore")
   // @PostMapping("/banner/store")
    public String store(@Valid @ModelAttribute("banner") Banner banner,
    BindingResult result,
    RedirectAttributes redirectAttributes,
    Model model,
    @RequestParam("files") MultipartFile files
    ) throws IOException
    {
       
    //    System.out.println("Hello Ajay"+image.getOriginalFilename()+"tech");
        if(result.hasErrors())
        {
            model.addAttribute("banner",banner);
            return "backend/banner/createOrUpdate";   
        }
        
        byte[] bytes = files.getBytes();
        java.nio.file.Path path = Paths.get( userDirectory + files.getOriginalFilename());
        Files.write(path, bytes);
        
        banner.setImage(files.getOriginalFilename());
        banner.setImageUrl("uploads/"+files.getOriginalFilename());
        banner.setIsActive(1);
        bannerRepository.save(banner);
        redirectAttributes.addFlashAttribute("successMessage", "Banner Added sucessfully...");
        return "redirect:/banner";   
    }

     @GetMapping("/banner/show/{id}")
    public String show(@PathVariable("id") int id,Model model)
    {
      System.out.println("Ajay"+bannerService.getUserById(id));  
      model.addAttribute("id",id);
      model.addAttribute("banner",bannerService.getUserById(id));
      return "backend/banner/show";   
    }
    
    @GetMapping("/banner/delete/{id}")
    public String delete(@PathVariable int id,Model model,RedirectAttributes redirectAttributes)
    {
       bannerService.deleteBannerById(id); 
       redirectAttributes.addFlashAttribute("successMessage", "Banner delete sucessfully...");
       return "redirect:banner";   
    }
    
    
    
}
