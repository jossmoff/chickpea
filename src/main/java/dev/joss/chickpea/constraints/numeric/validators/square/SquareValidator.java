package dev.joss.chickpea.constraints.numeric.validators.square;

import dev.joss.chickpea.constraints.numeric.Square;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.BigInteger;

public class SquareValidator implements ConstraintValidator<Square, Number> {

  @Override
  public boolean isValid(Number value, ConstraintValidatorContext context) {
    if (value == null) return true;

    if (value instanceof BigInteger) {
      return isPerfectSquareBigInteger((BigInteger) value);
    }

    if (value instanceof BigDecimal) {
      BigDecimal bigDecimalValue = (BigDecimal) value;
      return isPerfectSquareBigInteger(bigDecimalValue.toBigInteger());
    }

    double result = Math.sqrt(value.doubleValue());
    if (Double.isInfinite(result) || Double.isNaN(result)) return false;

    return Math.ceil(result) == Math.floor(result);
  }

  private static boolean isPerfectSquareBigInteger(BigInteger value) {
    BigInteger sqrt = value.sqrt();
    return sqrt.multiply(sqrt).equals(value);
  }
}
