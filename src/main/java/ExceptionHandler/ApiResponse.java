
package ExceptionHandler;

import Entity.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private String responseCode;
    private T data;
    private List<String> errors;
    // Constructor, getters, and setters
}