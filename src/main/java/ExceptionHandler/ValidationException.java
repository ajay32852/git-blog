
package ExceptionHandler;
import java.util.List;
public class ValidationException extends RuntimeException {
    private List<String> errors;

    public ValidationException(List<String> errors) {
        this.errors = errors;
    }
    public List<String> getErrors() {
        return errors;
    }
}
