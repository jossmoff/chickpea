package dev.joss.chickpea.constraints.str;

import static dev.joss.chickpea.utils.ConstraintViolationSetAssert.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class CurrencyCodeTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  public static final String correct = "USD";
  public static final String incorrect = "absldkfj23480924ljk2j34c";

  @Test
  public void testAnnotatedFieldsCausesExpectedViolation() {
    CurrencyCodeBeanFields CurrencyCodeBeanFields = new CurrencyCodeBeanFields();

    Set<ConstraintViolation<CurrencyCodeBeanFields>> violations =
        validator.validate(CurrencyCodeBeanFields);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }

  @Test
  public void testAnnotatedMethodsCausesExpectedViolation() {
    CurrencyCodeBeanMethods CurrencyCodeBeanMethods = new CurrencyCodeBeanMethods();

    Set<ConstraintViolation<CurrencyCodeBeanMethods>> violations =
        validator.validate(CurrencyCodeBeanMethods);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }

  @Test
  public void testAnnotatedTypesCausesExpectedViolation() {
    CurrencyCodeBeanType CurrencyCodeBeanType = new CurrencyCodeBeanType();

    Set<ConstraintViolation<CurrencyCodeBeanType>> violations =
        validator.validate(CurrencyCodeBeanType);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }

  // TODO: These tests are merely a guideline. You should make sure the tests cover the appropriate
  // functionality!
}

@Data
class CurrencyCodeBeanFields {

  @CurrencyCode private String correct = CurrencyCodeTest.correct;

  @CurrencyCode private String incorrect = CurrencyCodeTest.incorrect;
}

@Data
class CurrencyCodeBeanMethods {

  @CurrencyCode
  private String getCorrect() {
    return CurrencyCodeTest.correct;
  }

  @CurrencyCode
  private String getIncorrect() {
    return CurrencyCodeTest.incorrect;
  }
}

@Data
class CurrencyCodeBeanType {
  private List<@CurrencyCode String> correct = List.of(CurrencyCodeTest.correct);
  private List<@CurrencyCode String> incorrect = List.of(CurrencyCodeTest.incorrect);
}
