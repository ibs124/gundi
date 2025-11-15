package ibs124.gundi.validation.validator;

import org.springframework.stereotype.Component;

import ibs124.gundi.service.user.UserReadingService;
import ibs124.gundi.validation.constraint.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserReadingService userService;

    public UniqueEmailValidator(UserReadingService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.userService.existsByEmail(value);
    }

}
