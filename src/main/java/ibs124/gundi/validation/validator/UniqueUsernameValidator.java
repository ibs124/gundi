package ibs124.gundi.validation.validator;

import org.springframework.stereotype.Component;

import ibs124.gundi.service.auth.application.ValidationService;
import ibs124.gundi.validation.constraint.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final ValidationService service;

    public UniqueUsernameValidator(ValidationService userService) {
        this.service = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.service.isUsernameUnique(value);
    }

}
