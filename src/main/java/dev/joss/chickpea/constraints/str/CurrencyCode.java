package dev.joss.chickpea.constraints.str;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import dev.joss.chickpea.constraints.str.validators.CurrencyCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The annotated element must conform to the ISO-4217 format for currency codes.
 *
 * @see <a href="https://en.m.wikipedia.org/wiki/ISO_4217">ISO-4217 format</a>
 * @author Joss Moffatt
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = CurrencyCodeValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface CurrencyCode {
  String message() default "{chickpea.constraints.string.CurrencyCode}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
