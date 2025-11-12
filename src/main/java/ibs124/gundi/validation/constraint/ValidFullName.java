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

@NotBlank(message = UserMessagesConfig.FULL_NAME_BLANK)
@Pattern(regexp = RegexConfig.FULL_NAME, message = UserMessagesConfig.FULL_NAME_ERROR)
@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidFullName {

    String message() default UserMessagesConfig.FULL_NAME_ERROR;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
