package Requests;
import jakarta.validation.constraints.NotEmpty;
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
public class BannerRequest {

    @NotEmpty(message = "Title is Not Empty")
    @Size(min = 5, max = 50,message = "Title enter between 5 to 50 string")
    private String title;
    
    @Size(min = 5, max = 50,message = "Description enter between 5 to 50 string")
    private String description;
    
    private int isActive;
    
    @NotEmpty(message = "Image is Not Empty")
    private String image;
    
    //@NotEmpty(message = "Image is Not Empty")
 //   private MultipartFile image;
    
    private String imageUrl;


    
}
