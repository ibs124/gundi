package ibs124.gundi.validation.validator;

import org.springframework.stereotype.Component;

import ibs124.gundi.service.auth.user.ValidationService;
import ibs124.gundi.validation.constraint.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final ValidationService validationService;

    public UniqueEmailValidator(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.validationService.isEmailUnique(value);
    }

}
