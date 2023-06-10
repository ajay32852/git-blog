
package API.V1;

import Entity.Category;
import ExceptionHandler.ApiResponse;
import Service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category/")
public class CategoryAPIController {
 
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("list")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        ApiResponse<List<Category>> response = new ApiResponse<>();
        List<Category> allCategories = categoryService.getAllcategory();

        response.setStatus(true);
        response.setMessage("Successfully fetched categories");
        response.setResponseCode("200 OK");
        response.setData(allCategories);
        return ResponseEntity.ok(response);
    }
    
    
}
