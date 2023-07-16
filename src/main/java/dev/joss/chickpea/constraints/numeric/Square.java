package dev.joss.chickpea.constraints.numeric;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import dev.joss.chickpea.constraints.numeric.validators.square.SquareValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The annotated element must be a perfect square
 *
 * @author Joss Moffatt
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = SquareValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface Square {
  String message() default "{chickpea.constraints.string.Square}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
