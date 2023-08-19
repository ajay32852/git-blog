package API.V1;
import Entity.Post;
import Entity.Review;
import ExceptionHandler.ApiResponse;
import Helper.ImageUtils;
import Requests.CreatePostRequest;
import Requests.CreateReviewRequest;
import Service.ReviewService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/api/v1/review/")
public class ReviewAPIController {
    
    @Autowired
    private ReviewService reviewService;
    
    
    @GetMapping("list")
    public ResponseEntity<ApiResponse<List<Review>>>reviewList()
    {
       ApiResponse<List<Review>> response=new ApiResponse<>();
       List<Review> reviews=reviewService.getAllReviews();
       response.setData(reviews);   
       if(reviews.isEmpty())
       {
        response.setData(null);   
       }
       response.setStatus(true);
       response.setResponseCode("200 OK");
       response.setMessage("Fetch all reviews list");
       return ResponseEntity.ok(response);  
    }
    
    // view review by post id
    @GetMapping("list/{post_id}")
    public ResponseEntity<ApiResponse<List<Review>>>reviewListByPostId(@PathVariable("post_id") int postId)
    {
       ApiResponse<List<Review>> response=new ApiResponse<>();
       List<Review> reviews=reviewService.getAllReviewsByPostId(postId);
       response.setData(reviews);   
       if(reviews.isEmpty())
       {
        response.setData(null);   
       }
       response.setStatus(true);
       response.setResponseCode("200 OK");
       response.setMessage("Fetch all reviews list");
       return ResponseEntity.ok(response);  
    }
    
   

     @PostMapping("add-review")
     public ResponseEntity<ApiResponse<Review>> createReview(@Valid CreateReviewRequest request,Review review) throws IOException 
    {
      ApiResponse<Review> response=new ApiResponse<>(); 
      try{
        
        review.setPost_id(request.getPost_id() );
        review.setComment(request.getComment());
        review.setRating(request.getRating());
        review.setUser_id(request.getUser_id());
        review.setStatus(1); 
        response.setStatus(true);
        response.setMessage("Review added sucessfully.!");
        response.setData(reviewService.SaveReview(review));
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
     
     
     
     
     
     
    
    
}
