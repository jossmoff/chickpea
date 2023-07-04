package dev.joss.chickpea.constraints.str.validators;

import dev.joss.chickpea.constraints.str.CurrencyCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Currency;
import java.util.Set;

public class CurrencyCodeValidator implements ConstraintValidator<CurrencyCode, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
    return availableCurrencies.stream()
        .anyMatch(currency -> currency.getCurrencyCode().equalsIgnoreCase(value));
  }
}
