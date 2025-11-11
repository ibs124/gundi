package ibs124.gundi.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ibs124.gundi.config.Regexes;
import ibs124.gundi.config.message.UserMessages;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@NotBlank(message = UserMessages.PASSWORD_BLANK)
@Pattern(regexp = Regexes.PASSWORD, message = UserMessages.PASSWORD_ERROR)
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidPassword {
    String message() default UserMessages.PASSWORD_ERROR;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}