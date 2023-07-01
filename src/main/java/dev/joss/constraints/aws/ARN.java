package dev.joss.constraints.aws;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import dev.joss.constraints.aws.validators.ARNValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The annotated element must conform to the ARN format.
 *
 * @see <a href="https://docs.aws.amazon.com/IAM/latest/UserGuide/reference-arns.html">ARN
 *     Format</a>
 * @author Joss Moffatt
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = ARNValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface ARN {
  String message() default "{chickpea.constraints.string.ARN}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
