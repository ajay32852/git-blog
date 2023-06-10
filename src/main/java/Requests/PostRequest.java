package Requests;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRequest {

    @NotEmpty(message = "Title is Not Empty")
    @Size(min = 5, max = 200,message = "Title enter between 5 to 200 string")
    private String title;
    
    @Size(min = 5, max = 800,message = "Description enter between 5 to 800 string")
    private String description;
    
    @NotNull(message = "please enter the user_id")
    private int user_id;
    
    private int isActive;
    
    private String imageUrl;


    
}
