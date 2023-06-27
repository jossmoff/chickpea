package dev.joss.constraints.str;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import dev.joss.constraints.str.validators.IPv6Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The annotated element must conform to the IPv6 format.
 *
 * @author Joss Moffatt
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = IPv6Validator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface IPv6 {
  String message() default "{chickpea.constraints.string.IPv6}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
