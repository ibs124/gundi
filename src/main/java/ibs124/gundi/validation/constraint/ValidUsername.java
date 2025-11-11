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

@NotBlank(message = UserMessages.USERNAME_BLANK)
@Pattern(regexp = Regexes.USERNAME, message = UserMessages.USERNAME_ERROR)
@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_PARAMETER,
        ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidUsername {

    String message() default UserMessages.USERNAME_ERROR;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}