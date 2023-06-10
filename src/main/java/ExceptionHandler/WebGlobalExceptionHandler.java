
package ExceptionHandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class WebGlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e,Model model)
    {
        model.addAttribute("error",e.getMessage());
        return"error"; 
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleInvalidRequest(NoHandlerFoundException ex,Model model)
    {
        model.addAttribute("errorMessage", "Invalid request: The requested page does not exist");
        return"invalidRequest";
    }
    
    
    
}
