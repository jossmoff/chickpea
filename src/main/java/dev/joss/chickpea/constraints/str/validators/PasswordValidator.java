package dev.joss.chickpea.constraints.str.validators;

import dev.joss.chickpea.constraints.str.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;

public class PasswordValidator implements ConstraintValidator<Password, String> {

  private static final Set<Integer> SPECIAL_CHARS =
      " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".chars().boxed().collect(Collectors.toSet());

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

    // These constraints are implications converted to DNF to allow for shortcircuiting
    boolean lowerCaseConstraint =
        !annotation.requiresLowerCase() || value.chars().anyMatch(Character::isLowerCase);
    boolean upperCaseConstraint =
        !annotation.requiresUpperCase() || value.chars().anyMatch(Character::isUpperCase);
    boolean specialCharsConstraint =
        !annotation.requiresSpecialChar() || value.chars().anyMatch(SPECIAL_CHARS::contains);
    boolean numericConstraint =
        !annotation.requiresNumeric() || value.chars().anyMatch(Character::isDigit);

    return lowerCaseConstraint
        && upperCaseConstraint
        && specialCharsConstraint
        && numericConstraint;
  }
}
