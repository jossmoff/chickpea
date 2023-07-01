package dev.joss.constraints.aws.validators;

import static software.amazon.awssdk.arns.Arn.fromString;

import dev.joss.constraints.aws.ARN;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ARNValidator implements ConstraintValidator<ARN, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      fromString(value);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
