package dev.joss.constraints.str.validators;

import dev.joss.constraints.str.IPv6;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.InetAddressValidator;

public class IPv6Validator implements ConstraintValidator<IPv6, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && InetAddressValidator.getInstance().isValidInet6Address(value);
  }
}
