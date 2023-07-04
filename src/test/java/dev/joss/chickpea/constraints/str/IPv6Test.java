package dev.joss.chickpea.constraints.str;

import static dev.joss.chickpea.utils.ConstraintViolationSetAssert.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class IPv6Test {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  public static final String correct = "0000:0000:0000:0000:0000:0000:0000:0001";
  public static final String incorrect = "not-an-ip";

  @Test
  public void testAnnotatedFieldsCausesExpectedViolation() {
    IPv6BeanFields iPv6BeanFields = new IPv6BeanFields();

    Set<ConstraintViolation<IPv6BeanFields>> violations = validator.validate(iPv6BeanFields);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }

  @Test
  public void testAnnotatedMethodsCausesExpectedViolation() {
    IPv6BeanMethods iPv6BeanMethods = new IPv6BeanMethods();

    Set<ConstraintViolation<IPv6BeanMethods>> violations = validator.validate(iPv6BeanMethods);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }

  @Test
  public void testAnnotatedTypesCausesExpectedViolation() {
    IPv6BeanType iPv6BeanType = new IPv6BeanType();

    Set<ConstraintViolation<IPv6BeanType>> violations = validator.validate(iPv6BeanType);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }

  @Test
  public void testNullBeansCausesExpectedViolation() {
    IPv6BeanNulls iPv6BeanNulls = new IPv6BeanNulls();

    Set<ConstraintViolation<IPv6BeanNulls>> violations = validator.validate(iPv6BeanNulls);

    assertThat(violations).hasSize(2);
  }
}

@Data
class IPv6BeanFields {

  @IPv6 private String correct = IPv6Test.correct;

  @IPv6 private String incorrect = IPv6Test.incorrect;
}

@Data
class IPv6BeanMethods {

  @IPv6
  private String getCorrect() {
    return IPv6Test.correct;
  }

  @IPv6
  private String getIncorrect() {
    return IPv6Test.incorrect;
  }
}

@Data
class IPv6BeanType {
  private List<@IPv6 String> correctIps = List.of(IPv6Test.correct);
  private List<@IPv6 String> incorrectIps = List.of(IPv6Test.incorrect);
}

@Data
class IPv6BeanNulls {

  @IPv6 private String nullField = null;

  @IPv6
  private String getNullMethod() {
    return null;
  }
}
