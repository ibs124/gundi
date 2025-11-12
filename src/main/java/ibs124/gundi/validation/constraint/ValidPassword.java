package ibs124.gundi.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ibs124.gundi.config.RegexConfig;
import ibs124.gundi.config.message.UserMessagesConfig;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@NotBlank(message = UserMessagesConfig.PASSWORD_BLANK)
@Pattern(regexp = RegexConfig.PASSWORD, message = UserMessagesConfig.PASSWORD_ERROR)
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidPassword {
    String message() default UserMessagesConfig.PASSWORD_ERROR;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}