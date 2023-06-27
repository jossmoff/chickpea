package dev.joss.constraints.str.validators;

import dev.joss.constraints.str.IPv4;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.InetAddressValidator;

public class IPv4Validator implements ConstraintValidator<IPv4, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && InetAddressValidator.getInstance().isValidInet4Address(value);
  }
}
