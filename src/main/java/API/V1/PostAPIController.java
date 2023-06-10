package API.V1;


import Entity.Banner;
import Entity.Post;
import ExceptionHandler.ApiResponse;
import Requests.BannerRequest;
import Requests.PostRequest;
import Security.ApplicationProperties;
import Service.PostService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import Entity.Post;
import Helper.ImageUtils;
import Requests.CreatePostRequest;
import java.awt.print.Pageable;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/post/")
public class PostAPIController {
    
    @Autowired
    private PostService postService;
    @Autowired
    private final ApplicationProperties applicationProperties = null;
    private String userDirectory = "src/main/resources/static/uploads/";
    
    @GetMapping("list")
    public ResponseEntity<ApiResponse<List<Post>> > postList(Model model,
    @RequestParam(defaultValue = "0") int page,        
    @RequestParam(required = false) String keyword,
    @RequestParam(required = false) String status )
    {
        ApiResponse<List<Post>> response=new ApiResponse<>();
       // Pageable pageable = PageRequest.of(page, 10);
        
       List<Post> posts=postService.getAllPosts(keyword=null,status=null);
       response.setData(posts);   
       if(posts.isEmpty())
       {
        response.setData(null);   
       }
       response.setStatus(true);
       response.setResponseCode("200 OK");
       response.setMessage("Fetch all post list");
       return ResponseEntity.ok(response);  
    }
   
    
   //  Post create Demo
     
    @PostMapping("add-post")
     public ResponseEntity<ApiResponse<Post>> createPost(@Valid CreatePostRequest request,Post post) throws IOException 
    {
      ApiResponse<Post> response=new ApiResponse<>();  
        // File upload logic
      try{
        MultipartFile file = request.getFile();
        String imagePath = ImageUtils.saveImage(request.getFile(),"post");
        post.setImage(file.getOriginalFilename());
        post.setImageUrl(imagePath);
        post.setIsActive(1);
        post.setCategory_id(request.getCategory_id() );
        post.setTitle(request.getTitle());
        post.setUser_id(request.getUser_id());
        post.setDescription(request.getDescription());
       
        response.setStatus(true);
        response.setMessage("Post added sucessfully.!");
        response.setData(postService.saveData(post));
        response.setResponseCode("200 OK");
        return ResponseEntity.ok(response);
        }
        catch(Exception e )
        {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            return ResponseEntity.ok(response);    
        }
        
    }   
     
     
    @GetMapping("show/{id}")
    public ResponseEntity<ApiResponse<Post> > postShow(Model model,
    @PathVariable("id") int id
    )
    {
       ApiResponse<Post> response=new ApiResponse<>();
       Optional<Post> post=postService.getPostById(id);
         
       if(post.isEmpty())
       {
        response.setData(null);   
       }else{
        response.setData(post.get());  
       }
       response.setStatus(true);
       response.setResponseCode("200 OK");
       response.setMessage("Fetch all post data");
       return ResponseEntity.ok(response);  
    }
  
    @GetMapping("delete/{id}")
    public ResponseEntity<ApiResponse<String> > postDelete(Model model,
    @PathVariable("id") int id
    )
    {
       ApiResponse<String> response=new ApiResponse<>();
       try{
       postService.deletePostById(id);
       response.setStatus(true);
       response.setResponseCode("200 OK");
       response.setMessage("Post deleted Done ");
       return ResponseEntity.ok(response); 
       }catch(Exception e)
       {
       response.setStatus(false);
       response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
       response.setMessage(e.getMessage());
       return ResponseEntity.ok(response);    
       }
       
    }
     
    
    
    
    
    
    
    
  
    
    
  
    
    
    
}
