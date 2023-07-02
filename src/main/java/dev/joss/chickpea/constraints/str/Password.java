package dev.joss.chickpea.constraints.str;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import dev.joss.chickpea.constraints.str.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The annotated element must conform to a set of commonly used password validation constraints. The
 * OWASP and Google recommendations for password security means there are 6 configurable
 * constraints:
 *
 * <ul>
 *   <li>Mininmum Length (default 8)
 *   <li>Maximum Length (default 64)
 *   <li>Requires Lower Case (defaults true)
 *   <li>Requires Upper Case (defaults true)
 *   <li>Requires Special Characters (defaults true)
 *   <li>Requires Numeric (defaults true)
 * </ul>
 *
 * The constraints starting with required follow logical implication. That is, if a constaint is
 * marked as not required, but it is satisfied then it is still a valid password. The length
 * constraints don't follow this.
 *
 * @author Joss Moffatt
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface Password {

  int minLength() default 8;

  int maxLength() default 64;

  boolean requiresLowerCase() default true;

  boolean requiresUpperCase() default true;

  boolean requiresSpecialChar() default true;

  boolean requiresNumeric() default true;

  String message() default "{chickpea.constraints.string.Password}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
