
package Requests;

import ExceptionHandler.FileSize;
import ExceptionHandler.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreatePostRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @Size(min = 5, max = 800,message = "Description enter between 5 to 800 string")
    private String description;

     @NotNull(message = "please enter the user_id")
     private int user_id;
     
     @NotNull(message = "Please Enter Category id")
     private int category_id;
     
     
      @NotEmpty(message = "File Should be select")
      @NotNull(message = "File is required")
      @FileSize(max = "5MB", message = "File size exceeds the limit of 5MB")
      @FileType(types = {"image/jpeg", "image/png","image/jfif"}, message = "Only JPEG and PNG  and  JFIF files are allowed")
      private MultipartFile file;
    
      private int isActive;

      private String imageUrl;

    // Getter and setter methods
}