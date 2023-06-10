package API.V1;

import Entity.Banner;
import ExceptionHandler.ApiResponse;
import Requests.BannerRequest;
import Service.BannerService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/banner/")
public class BannerAPIController {
 
    @Autowired
    private BannerService bannerService;
    
    private String userDirectory = "src/main/resources/static/uploads/";
     
    
    @GetMapping("list")
    public ResponseEntity<ApiResponse<List<Banner>>> getAllBanner() {
        ApiResponse<List<Banner>> response = new ApiResponse<>();
        List<Banner> allBanner = bannerService.getBannersByIsActive(1);
        response.setStatus(true);
        response.setMessage("Successfully fetched banner");
        response.setResponseCode("200 OK");
        response.setData(allBanner);
        return ResponseEntity.ok(response);
        //return ResponseEntity.ok(response); 
    }
    
    @GetMapping("show/{id}")
    public ResponseEntity<ApiResponse<Banner>>getBannerById(@PathVariable("id")int id )
    {
        ApiResponse<Banner> response=new ApiResponse<>();
        Banner banner = bannerService.findBannerById(id);
        response.setStatus(true);
        response.setMessage("Successfully data fetched banner");
        response.setResponseCode("200 OK");
        response.setData(banner);
        return ResponseEntity.ok(response);
        
    }
    
    
//    public ResponseEntity<ApiResponse<Banner>> addBanner(@Valid @RequestBody BannerRequest bannerRequest,
//     BindingResult result,
//     @RequestParam("image") MultipartFile image) throws IOException
//    {
      @PostMapping("add-banner")
      public ResponseEntity<ApiResponse<Banner>> addBanner(
        @Valid BannerRequest bannerRequest,
        @RequestParam("image") MultipartFile image,
        @RequestParam("title") String title,
        @RequestParam("description") String description,
        BindingResult result,Banner banner
         ) throws IOException 
      {
        // Check if the file is empty
        ApiResponse<Banner> response =new ApiResponse<>();
        if(result.hasErrors())
        {
            response.setStatus(false);
            response.setMessage("Validation failed");
            response.setResponseCode("400 Bad Request");
            response.setErrors(getAllValidationErrors(result));
            return ResponseEntity.badRequest().body(response);
        }
        byte[] bytes = image.getBytes();
        java.nio.file.Path path = Paths.get( userDirectory + image.getOriginalFilename());
        Files.write(path, bytes);
        
        banner.setImage(image.getOriginalFilename());
        banner.setImageUrl("uploads/"+image.getOriginalFilename());
        banner.setIsActive(1);
        banner.setTitle(title);
        banner.setDescription(description);
        
       
         response.setStatus(true);
         response.setMessage("Banner added sucessfully.!");
         response.setData(null);
         response.setResponseCode("200 OK");
         return ResponseEntity.ok(response);
    }
      
      
    
     private List<String> getAllValidationErrors(BindingResult result)
    {
        List<String> errors=new ArrayList<>();
        for(FieldError error :result.getFieldErrors())
        {
           errors.add(error.getDefaultMessage());
        }
        return errors;
    }
    
    
    
    
    
    
    
    
    
    
}
