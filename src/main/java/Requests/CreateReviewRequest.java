
package Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateReviewRequest {
    
     @NotNull(message = "enter the post id" )
     private int  post_id;

     @Size(min = 5, max = 800,message = "comment enter between 5 to 800 string")
     private String comment;

     @NotNull(message = "please enter the user_id")
     private int user_id;
     
     @NotNull(message = "enter rating value")
     private int rating;
    
    
}
