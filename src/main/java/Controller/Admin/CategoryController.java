package Controller.Admin;
import Entity.Category;
import Service.CategoryService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    private String userDirectory = "src/main/resources/static/uploads/";
    
    
   @GetMapping("/category")
    public String index(Model model)
    {
        System.out.println("AjayTech"+categoryService.getAllcategory());
        model.addAttribute("title","Category Manager");
        model.addAttribute("show_title","All Category");
        model.addAttribute("categorys",categoryService.getAllcategory());
        return "backend/Category/index";
    }
    
    //@GetMapping("category/create")
    @GetMapping("/ccreate")
    public String create(Model model,Category category)
    {
      model.addAttribute("category",category);  
      model.addAttribute("title","Category Manager");
      model.addAttribute("show_title","Add Category Manger");
      return "backend/Category/createOrUpdate";   
    }
    
    @PostMapping("/cstore")
    public String store(@Valid @ModelAttribute("category") Category category,
    BindingResult result,
    RedirectAttributes redirectAttributes,
    Model model,
    @RequestParam("files") MultipartFile files
    ) throws IOException
    { 
        if(result.hasErrors())
        {
            System.out.println("ErrorAjay"+result.getErrorCount());
            model.addAttribute("category",category);
            return "backend/Category/createOrUpdate";   
        }
        
        byte[] bytes = files.getBytes();
        java.nio.file.Path path = Paths.get( userDirectory + files.getOriginalFilename());
        Files.write(path, bytes);

        category.setImage(files.getOriginalFilename());
        category.setImageUrl("uploads/"+files.getOriginalFilename());
        category.setIsActive(1);
        category.setIsPopular(1);
        categoryService.StoreCategory(category);
        redirectAttributes.addFlashAttribute("successMessage", "Category Added sucessfully...");
        return "redirect:/category";    
    }
    
    @GetMapping("category/show")
    public String show()
    {
      return "backend/Category/createOrUpdate";   
    }
    
    @GetMapping("/category/delete/{id}")
    public String delete(@PathVariable int id ,Model model,RedirectAttributes redirectAttributes )
    {
    System.out.println("Id"+id);   
    categoryService.deleteCategoryById(id);
    redirectAttributes.addFlashAttribute("successMessage", "Category delete sucessfully...");
    return "redirect:category"; 
    }  
    
    
    
}
