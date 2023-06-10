
package Controller.Admin;

import Entity.Post;
import Helper.ImageUtils;
import Service.CategoryService;
import Service.PostService;
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
@RequestMapping("admin/post")
public class PostController {
    
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public String index(Model model,
    @RequestParam(required = false) String keyword,
    @RequestParam(required = false) String status
            
    )
    {
        model.addAttribute("title","Show All Post");
        model.addAttribute("show_title","Show all Post");
        model.addAttribute("posts", postService.getAllPosts(keyword, status));
        return "backend/post/index";
    }

    
    @GetMapping("/create")
    public String create(Model model,Post post)
    {
        System.out.println("hello ajay");
        model.addAttribute("title","Add Post Manager");
        model.addAttribute("show_title","Show all Post");
        model.addAttribute("post",post);
        model.addAttribute("categories",categoryService.getAllcategory());
        return "backend/post/createOrUpdate"; 
    }
    
    @PostMapping("/store")
    public String Store(
     @Valid @ModelAttribute("post") Post post,
     BindingResult  result,
     RedirectAttributes redirectAttributes,
     @RequestParam("files") MultipartFile files
    ) throws IOException
    {
        System.out.println("Data123"+post.toString());
        if(result.hasErrors())
        {
            return "backend/post/createOrUpdate";
        }
        
        String imagePath = ImageUtils.saveImage(files,"post");
        post.setImage(files.getOriginalFilename());
        post.setImageUrl(imagePath); 
        post.setIsActive(post.getIsActive());
        post.setCategory_id(post.getCategory_id());
        post.setIsPopular(1);
        System.out.println("QueryAjay15"+post.toString());
        postService.saveData(post);
        redirectAttributes.addFlashAttribute("successMessage", "Post Added sucessfully...");
        return "redirect:/post";   
    }
    
   @GetMapping("show/{id}") 
    public String show(@PathVariable("id") int id)
    {
        
        return "backend/post/show";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes)
    {
       // System.out.println("uuid"+id); 
      //  categoryService.deletePostById(id); 
        redirectAttributes.addFlashAttribute("successMessage", "Post hasbeen deleted sucessfully...");
        return "redirect:/post";    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
