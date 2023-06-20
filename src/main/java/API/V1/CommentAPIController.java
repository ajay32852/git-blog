
package API.V1;
import Entity.Comment;
import Entity.User;
import ExceptionHandler.ApiResponse;
import Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/commnets")
public class CommentAPIController {
    
    @Autowired
    private CommentService commentService;
 
    
    public ResponseEntity<ApiResponse<Comment>> getList()
    {
        ApiResponse<Comment> response = new ApiResponse<>();
        response.setStatus(true);
        response.setMessage("User found");
        response.setResponseCode("200 OK");
       // response.setData(comments);
        return ResponseEntity.ok(response);      
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
