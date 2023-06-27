package dev.joss.utils;

import jakarta.validation.ConstraintViolation;
import org.assertj.core.api.AbstractAssert;

import java.util.Set;


public class ConstraintViolationSetAssert<T> extends AbstractAssert<ConstraintViolationSetAssert<T>, Set<ConstraintViolation<T>>> {

    private ConstraintViolationSetAssert(Set<ConstraintViolation<T>> actual) {
        super(actual, ConstraintViolationSetAssert.class);
    }

    public static <T> ConstraintViolationSetAssert<T> assertThat(Set<ConstraintViolation<T>> actual) {
        return new ConstraintViolationSetAssert<>(actual);
    }

    public ConstraintViolationSetAssert<T> hasSize(int size) {
        org.assertj.core.api.Assertions.assertThat(actual).hasSize(size);
        return this;
    }

    public ConstraintViolationSetAssert<T> containsInvalidValue(Object invalidValue) {
        isNotNull();

        for (ConstraintViolation<T> violation : actual) {
            if (violation.getInvalidValue().equals(invalidValue)) {
                return this;
            }
        }

        failWithMessage("Expected to find a violation with an invalid value <%s>, but none were found",
                invalidValue);
        return this;
    }
}
