package dev.joss.chickpea.constraints.aws;

import static dev.joss.utils.ConstraintViolationSetAssert.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ARNTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  public static final String correctArn =
      "arn:aws:lambda:us-east-1:123456789012:function:my-function";
  public static final String incorrectArn = "not-an-arn";

  @ParameterizedTest
  @CsvFileSource(resources = "/aws/arns.csv", numLinesToSkip = 1)
  public void testAnnotatedFieldsWithValidARNsCausesNoViolations(String arn) {
    CorrectArnFields correctArnFields = new CorrectArnFields(arn);

    Set<ConstraintViolation<CorrectArnFields>> violations = validator.validate(correctArnFields);

    assertThat(violations).isEmpty();
  }

  @Test
  public void testAnnotatedFieldsWithInvalidARNsCausesNoViolations() {
    IncorrectArnFields incorrectArnFields = new IncorrectArnFields();

    Set<ConstraintViolation<IncorrectArnFields>> violations =
        validator.validate(incorrectArnFields);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrectArn);
  }

  @Test
  public void testAnnotatedMethodsCausesExpectedViolation() {
    ARNBeanMethods ARNBeanMethods = new ARNBeanMethods();

    Set<ConstraintViolation<ARNBeanMethods>> violations = validator.validate(ARNBeanMethods);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrectArn);
  }

  @Test
  public void testAnnotatedTypesCausesExpectedViolation() {
    ARNBeanType ARNBeanType = new ARNBeanType();

    Set<ConstraintViolation<ARNBeanType>> violations = validator.validate(ARNBeanType);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrectArn);
  }
}

@AllArgsConstructor
@Data
class CorrectArnFields {
  @ARN private String correctArn;
}

@Data
class IncorrectArnFields {
  @ARN private String incorrectArn = ARNTest.incorrectArn;
}

@Data
class ARNBeanMethods {

  @ARN
  private String getCorrect() {
    return ARNTest.correctArn;
  }

  @ARN
  private String getIncorrect() {
    return ARNTest.incorrectArn;
  }
}

@Data
class ARNBeanType {
  private List<@ARN String> correctArns = List.of(ARNTest.correctArn);
  private List<@ARN String> incorrectArns = List.of(ARNTest.incorrectArn);
}
