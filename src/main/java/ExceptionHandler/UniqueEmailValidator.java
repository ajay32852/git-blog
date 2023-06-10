package ExceptionHandler;

import Repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator <UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    public void initialize(UniqueEmail constraintAnnotation) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }
       //return !userRepository.findByEmail(email);
        return !userRepository.existsByEmail(email);
    }
}
