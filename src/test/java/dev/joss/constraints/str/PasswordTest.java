package dev.joss.constraints.str;

import static dev.joss.utils.ConstraintViolationSetAssert.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class PasswordTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  public static final String validPassword = "ThisPassw0rdHasAllTheConstraints!";
  public static final String tooShortPassword = "abc";
  public static final String tooLongPassword =
      "this-is-a-really-really-really-really-really-really-long-password!!!!";
  public static final String missingLowerCasePassword = "THIS_PASSW0RD_ONLY_MISSES_LOWERCASE";
  public static final String missingUpperCasePassword = "this_passw0rd_only_misses_uppercase";
  public static final String missingSpecialCharacterPassword = "ThisPassw0rdHasNoSpecialChars";
  public static final String missingNumericCharacterPassword = "This_password_has_no_special_chars";

  @Test
  public void testAnnotatedFieldsCausesExpectedViolation() {
    PasswordBeanFields passwordBeanFields = new PasswordBeanFields();

    Set<ConstraintViolation<PasswordBeanFields>> violations =
        validator.validate(passwordBeanFields);

    assertThat(violations).hasSize(6);
    assertThat(violations)
        .containsInvalidValues(
            tooShortPassword,
            tooLongPassword,
            missingLowerCasePassword,
            missingUpperCasePassword,
            missingSpecialCharacterPassword,
            missingNumericCharacterPassword);
  }

  @Test
  public void testRequiredConstraintsOffButStillPresentDoesntViolate() {
    PasswordBeanConstraintsOff passwordBeanConstraintsOff = new PasswordBeanConstraintsOff();

    Set<ConstraintViolation<PasswordBeanConstraintsOff>> violations =
        validator.validate(passwordBeanConstraintsOff);

    assertThat(violations).isEmpty();
  }

  @Test
  public void testAnnotatedMethodsCausesExpectedViolation() {
    PasswordBeanMethods passwordBeanMethods = new PasswordBeanMethods();

    Set<ConstraintViolation<PasswordBeanMethods>> violations =
        validator.validate(passwordBeanMethods);

    assertThat(violations).hasSize(6);
    assertThat(violations)
        .containsInvalidValues(
            tooShortPassword,
            tooLongPassword,
            missingLowerCasePassword,
            missingUpperCasePassword,
            missingSpecialCharacterPassword,
            missingNumericCharacterPassword);
  }

  @Test
  public void testAnnotatedTypesCausesExpectedViolation() {
    PasswordBeanType passwordBeanType = new PasswordBeanType();

    Set<ConstraintViolation<PasswordBeanType>> violations = validator.validate(passwordBeanType);

    assertThat(violations).hasSize(6);
    assertThat(violations)
        .containsInvalidValues(
            tooShortPassword,
            tooLongPassword,
            missingLowerCasePassword,
            missingUpperCasePassword,
            missingSpecialCharacterPassword,
            missingNumericCharacterPassword);
  }

  @Test
  public void testNullBeansCausesExpectedViolation() {
    PasswordBeanNulls passwordBeanNulls = new PasswordBeanNulls();

    Set<ConstraintViolation<PasswordBeanNulls>> violations = validator.validate(passwordBeanNulls);

    assertThat(violations).hasSize(2);
  }
}

@Data
class PasswordBeanFields {
  @Password private String correct = PasswordTest.validPassword;
  @Password private String tooShort = PasswordTest.tooShortPassword;
  @Password private String tooLong = PasswordTest.tooLongPassword;
  @Password private String noLowerCase = PasswordTest.missingLowerCasePassword;
  @Password private String noUpperCase = PasswordTest.missingUpperCasePassword;
  @Password private String noSpecialChar = PasswordTest.missingSpecialCharacterPassword;
  @Password private String noNumericlChar = PasswordTest.missingNumericCharacterPassword;
}

@Data
class PasswordBeanConstraintsOff {
  @Password(
      requiresLowerCase = false,
      requiresUpperCase = false,
      requiresSpecialChar = false,
      requiresNumeric = false)
  private String correct = PasswordTest.validPassword;
}

@Data
class PasswordBeanMethods {

  @Password
  private String getCorrect() {
    return PasswordTest.validPassword;
  }

  @Password
  private String getTooShort() {
    return PasswordTest.tooShortPassword;
  }

  @Password
  private String getTooLong() {
    return PasswordTest.tooLongPassword;
  }

  @Password
  private String getNoLowerCase() {
    return PasswordTest.missingLowerCasePassword;
  }

  @Password
  private String getNoUpperCase() {
    return PasswordTest.missingUpperCasePassword;
  }

  @Password
  private String getNoSpecialChar() {
    return PasswordTest.missingSpecialCharacterPassword;
  }

  @Password
  private String getNoNumericlChar() {
    return PasswordTest.missingNumericCharacterPassword;
  }
}

@Data
class PasswordBeanType {
  private List<@Password String> correct = List.of(PasswordTest.validPassword);
  private List<@Password String> tooShort = List.of(PasswordTest.tooShortPassword);
  private List<@Password String> tooLong = List.of(PasswordTest.tooLongPassword);
  private List<@Password String> noLowerCase = List.of(PasswordTest.missingLowerCasePassword);
  private List<@Password String> noUpperCase = List.of(PasswordTest.missingUpperCasePassword);
  private List<@Password String> noSpecialChar =
      List.of(PasswordTest.missingSpecialCharacterPassword);
  private List<@Password String> noNumericlChar =
      List.of(PasswordTest.missingNumericCharacterPassword);
}

@Data
class PasswordBeanNulls {

  @Password private String nullField = null;

  @Password
  private String getNullMethod() {
    return null;
  }
}
