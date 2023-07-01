package dev.joss.constraints.str.validators;

import static java.util.stream.Collectors.toSet;

import dev.joss.constraints.str.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.IntStream;

public class PasswordValidator implements ConstraintValidator<Password, String> {

  private static final Set<Character> SPECIAL_CHARS =
      " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".chars().mapToObj(i -> (char) i).collect(toSet());

  private Password annotation;

  @Override
  public void initialize(Password constraintAnnotation) {
    this.annotation = constraintAnnotation;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (null == value) {
      return false;
    }

    if (value.length() < annotation.minLength() || value.length() > annotation.maxLength()) {
      return false;
    }

    IntStream passwordChars = value.chars();

    // These constraints are implications converted to DNF to allow for shortcircuiting
    boolean lowerCaseConstraint =
        !annotation.requiresLowerCase() || passwordChars.anyMatch(Character::isLowerCase);
    boolean upperCaseConstraint =
        !annotation.requiresLowerCase() || passwordChars.anyMatch(Character::isUpperCase);
    boolean specialCharsConstraint =
        !annotation.requiresSpecialChar() || passwordChars.anyMatch(SPECIAL_CHARS::contains);
    boolean numericConstraint =
        !annotation.requiresNumeric() || passwordChars.anyMatch(Character::isDigit);

    return lowerCaseConstraint
        && upperCaseConstraint
        && specialCharsConstraint
        && numericConstraint;
  }
}
