package ibs124.gundi.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ibs124.gundi.constant.Messages;
import ibs124.gundi.constant.Regexes;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@NotBlank(message = Messages.BLANK_FIELD)
@Pattern(regexp = Regexes.FULL_NAME, message = Messages.FULL_NAME_FORMAT_ERROR)
@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidFullName {

    String message() default Messages.FULL_NAME_FORMAT_ERROR;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
