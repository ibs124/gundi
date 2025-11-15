package ibs124.gundi.validation.validator;

import org.springframework.stereotype.Component;

import ibs124.gundi.service.user.UserReadingService;
import ibs124.gundi.validation.constraint.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserReadingService userService;

    public UniqueEmailValidator(UserReadingService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }

}
