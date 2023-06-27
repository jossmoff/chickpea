package dev.joss.constraints.str;

import static dev.joss.utils.ConstraintViolationSetAssert.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class IPv4Test {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  public static final String correct = "127.0.0.1";
  public static final String incorrect = "not-an-ip";

  @Test
  public void testFields() {
    IPv4BeanFields iPv4BeanFields = new IPv4BeanFields();

    Set<ConstraintViolation<IPv4BeanFields>> violations = validator.validate(iPv4BeanFields);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }

  @Test
  public void testMethods() {
    IPv4BeanMethods iPv4BeanMethods = new IPv4BeanMethods();

    Set<ConstraintViolation<IPv4BeanMethods>> violations = validator.validate(iPv4BeanMethods);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }

  @Test
  public void testType() {
    IPv4BeanType iPv4BeanType = new IPv4BeanType();

    Set<ConstraintViolation<IPv4BeanType>> violations = validator.validate(iPv4BeanType);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }

  @Test
  public void testNullChecks() {
    IPv4BeanNulls iPv4BeanNulls = new IPv4BeanNulls();

    Set<ConstraintViolation<IPv4BeanNulls>> violations = validator.validate(iPv4BeanNulls);

    assertThat(violations).hasSize(2);
  }
}

@Data
class IPv4BeanFields {

  @IPv4 private String correct = IPv4Test.correct;

  @IPv4 private String incorrect = IPv4Test.incorrect;
}

@Data
class IPv4BeanMethods {

  @IPv4
  private String getCorrect() {
    return IPv4Test.correct;
  }

  @IPv4
  private String getIncorrect() {
    return IPv4Test.incorrect;
  }
}

@Data
class IPv4BeanType {
  private List<@IPv4 String> correctIps = List.of(IPv4Test.correct);
  private List<@IPv4 String> incorrectIps = List.of(IPv4Test.incorrect);
}

@Data
class IPv4BeanNulls {

  @IPv4 private String nullField = null;

  @IPv4
  private String getNullMethod() {
    return null;
  }
}
