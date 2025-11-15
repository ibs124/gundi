package ibs124.gundi.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ibs124.gundi.config.message.ErrorMessageConfig;
import ibs124.gundi.validation.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { UniqueEmailValidator.class })
public @interface UniqueEmail {

    String message() default ErrorMessageConfig.EMAIL_TAKEN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
