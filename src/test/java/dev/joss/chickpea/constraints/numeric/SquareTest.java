package dev.joss.chickpea.constraints.numeric;

import static dev.joss.chickpea.utils.ConstraintViolationSetAssert.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class SquareTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  public static final int negative = -1;
  public static final double nanDouble = Double.NaN;
  public static final double positiveInfDouble = Double.POSITIVE_INFINITY;
  public static final double negativeInfDouble = Double.NEGATIVE_INFINITY;

  public static final int correctInt = 4;
  public static final int incorrectInt = 3;

  public static final byte correctByte = 4;
  public static final byte incorrectByte = 3;

  public static final short correctShort = 4;
  public static final short incorrectShort = 3;

  public static final long correctLong = 4;
  public static final long incorrectLong = 3;

  public static final float correctFloat = 4;
  public static final float incorrectFloat = 3;

  public static final double correctDouble = 4;
  public static final double incorrectDouble = 3;

  public static final BigInteger correctBigInteger =
      new BigInteger("100000000000000000000000000000000000000000000");
  public static final BigInteger incorrectBigInteger =
      new BigInteger("100000000000000000000000000000000000000000001");

  public static final BigDecimal correctBigDecimal =
      new BigDecimal("100000000000000000000000000000000000000000000.0");
  public static final BigDecimal incorrectBigDecimal =
      new BigDecimal("10101010101010101.10101010101");

  @Test
  public void testAnnotatedFieldsCausesExpectedViolation() {
    SquareBeanFields squareBeanFields = new SquareBeanFields();

    Set<ConstraintViolation<SquareBeanFields>> violations = validator.validate(squareBeanFields);

    assertThat(violations).hasSize(12);
    assertThat(violations)
        .containsInvalidValues(
            negative,
            nanDouble,
            positiveInfDouble,
            negativeInfDouble,
            incorrectInt,
            incorrectByte,
            incorrectDouble,
            incorrectFloat,
            incorrectLong,
            incorrectShort,
            incorrectBigInteger,
            incorrectBigDecimal);
  }

  @Test
  public void testAnnotatedMethodsCausesExpectedViolation() {
    SquareBeanMethods squareBeanMethods = new SquareBeanMethods();

    Set<ConstraintViolation<SquareBeanMethods>> violations = validator.validate(squareBeanMethods);

    assertThat(violations).hasSize(12);
    assertThat(violations)
        .containsInvalidValues(
            negative,
            nanDouble,
            positiveInfDouble,
            negativeInfDouble,
            incorrectInt,
            incorrectByte,
            incorrectDouble,
            incorrectFloat,
            incorrectLong,
            incorrectShort,
            incorrectBigInteger,
            incorrectBigDecimal);
  }

  @Test
  public void testAnnotatedTypesCausesExpectedViolation() {
    SquareBeanType squareBeanType = new SquareBeanType();

    Set<ConstraintViolation<SquareBeanType>> violations = validator.validate(squareBeanType);

    assertThat(violations).hasSize(12);
    assertThat(violations)
        .containsInvalidValues(
            negative,
            nanDouble,
            positiveInfDouble,
            negativeInfDouble,
            incorrectInt,
            incorrectByte,
            incorrectDouble,
            incorrectFloat,
            incorrectLong,
            incorrectShort,
            incorrectBigInteger,
            incorrectBigDecimal);
  }
}

@Data
class SquareBeanFields {

  @Square private Integer negative = SquareTest.negative;
  @Square private Double nanDouble = SquareTest.nanDouble;
  @Square private Double positiveInfDouble = SquareTest.positiveInfDouble;
  @Square private Double negativeInfDouble = SquareTest.negativeInfDouble;

  @Square private Integer correctInteger = SquareTest.correctInt;
  @Square private Integer incorrectInteger = SquareTest.incorrectInt;

  @Square private Byte correctByte = SquareTest.correctByte;
  @Square private Byte incorrectByte = SquareTest.incorrectByte;

  @Square private Short correctShort = SquareTest.correctShort;
  @Square private Short incorrectShort = SquareTest.incorrectShort;

  @Square private Long correctLong = SquareTest.correctLong;
  @Square private Long incorrectLong = SquareTest.incorrectLong;

  @Square private Float correctFloat = SquareTest.correctFloat;
  @Square private Float incorrectFloat = SquareTest.incorrectFloat;

  @Square private Double correctDouble = SquareTest.correctDouble;
  @Square private Double incorrectDouble = SquareTest.incorrectDouble;

  @Square private BigInteger correctBigInteger = SquareTest.correctBigInteger;
  @Square private BigInteger incorrectBigInteger = SquareTest.incorrectBigInteger;

  @Square private BigDecimal correctBigDecimal = SquareTest.correctBigDecimal;
  @Square private BigDecimal incorrectBigDecimal = SquareTest.incorrectBigDecimal;
}

@Data
class SquareBeanMethods {

  @Square
  private Integer getNegative() {
    return SquareTest.negative;
  }

  @Square
  private Double getNanDouble() {
    return SquareTest.nanDouble;
  }

  @Square
  private Double getPositiveInfDouble() {
    return SquareTest.positiveInfDouble;
  }

  @Square
  private Double getNegativeInfDouble() {
    return SquareTest.negativeInfDouble;
  }

  @Square
  private Integer getCorrectInt() {
    return SquareTest.correctInt;
  }

  @Square
  private Integer getIncorrectInt() {
    return SquareTest.incorrectInt;
  }

  @Square
  private Byte getCorrectByte() {
    return SquareTest.correctByte;
  }

  @Square
  private Byte getIncorrectByte() {
    return SquareTest.incorrectByte;
  }

  @Square
  private Short getCorrectShort() {
    return SquareTest.correctShort;
  }

  @Square
  private Short getIncorrectShort() {
    return SquareTest.incorrectShort;
  }

  @Square
  private Long getCorrectLong() {
    return SquareTest.correctLong;
  }

  @Square
  private Long getIncorrectLong() {
    return SquareTest.incorrectLong;
  }

  @Square
  private Float getCorrectFloat() {
    return SquareTest.correctFloat;
  }

  @Square
  private Float getIncorrectFloat() {
    return SquareTest.incorrectFloat;
  }

  @Square
  private Double getCorrectDouble() {
    return SquareTest.correctDouble;
  }

  @Square
  private Double getIncorrectDouble() {
    return SquareTest.incorrectDouble;
  }

  @Square
  private BigInteger getCorrectBigInteger() {
    return SquareTest.correctBigInteger;
  }

  @Square
  private BigInteger getIncorrectBigInteger() {
    return SquareTest.incorrectBigInteger;
  }

  @Square
  private BigDecimal getCorrectBigDecimal() {
    return SquareTest.correctBigDecimal;
  }

  @Square
  private BigDecimal getIncorrectBigDecimal() {
    return SquareTest.incorrectBigDecimal;
  }
}

@Data
class SquareBeanType {
  private List<@Square Integer> negative = List.of(SquareTest.negative);
  private List<@Square Double> nanDouble = List.of(SquareTest.nanDouble);
  private List<@Square Double> positiveInfDouble = List.of(SquareTest.positiveInfDouble);
  private List<@Square Double> negativeInfDouble = List.of(SquareTest.negativeInfDouble);

  private List<@Square Integer> correctInteger = List.of(SquareTest.correctInt);
  private List<@Square Integer> incorrectInteger = List.of(SquareTest.incorrectInt);

  private List<@Square Byte> correctByte = List.of(SquareTest.correctByte);
  private List<@Square Byte> incorrectByte = List.of(SquareTest.incorrectByte);

  private List<@Square Short> correctShort = List.of(SquareTest.correctShort);
  private List<@Square Short> incorrectShort = List.of(SquareTest.incorrectShort);

  private List<@Square Long> correctLong = List.of(SquareTest.correctLong);
  private List<@Square Long> incorrectLong = List.of(SquareTest.incorrectLong);

  private List<@Square Float> correctFloat = List.of(SquareTest.correctFloat);
  private List<@Square Float> incorrectFloat = List.of(SquareTest.incorrectFloat);

  private List<@Square Double> correctDouble = List.of(SquareTest.correctDouble);
  private List<@Square Double> incorrectDouble = List.of(SquareTest.incorrectDouble);

  private List<@Square BigInteger> correctBigInteger = List.of(SquareTest.correctBigInteger);
  private List<@Square BigInteger> incorrectBigInteger = List.of(SquareTest.incorrectBigInteger);

  private List<@Square BigDecimal> correctBigDecimal = List.of(SquareTest.correctBigDecimal);
  private List<@Square BigDecimal> incorrectBigDecimal = List.of(SquareTest.incorrectBigDecimal);
}
