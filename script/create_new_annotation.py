import os

def create_dirs_if_non_existent(*dirs):
  for dir in dirs:
    if not os.path.exists(dir):
      os.makedirs(dir)

annotation_name = input('🫘 Annotation name: ')
annotation_package_offset = input('🫘 Package offset from dev.joss.chickpea.constraints (e.g. str, aws, or something new): ')
annotation_package = f'dev.joss.chickpea.constraints.{annotation_package_offset}'
annotation_validator_package = f'{annotation_package}.validator'
author_name = input('🫘 Author name: ')

annotation_interface = f'''package {annotation_package};

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import {annotation_package}.validators.{annotation_name}Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * TODO: Write Javadoc description
 *
 * @author {author_name}
 * @since TODO: Add version
 */
@Documented
@Constraint(validatedBy = {annotation_name}Validator.class)
@Target({{METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE}})
@Retention(RUNTIME)
public @interface {annotation_name} {{
  String message() default "{{chickpea.constraints.string.{annotation_name}}}";

  Class<?>[] groups() default {{}};

  Class<? extends Payload>[] payload() default {{}};
}}'''

annotation_validator = f'''package {annotation_validator_package};

import {annotation_package}.{annotation_name};
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class {annotation_name}Validator implements ConstraintValidator<{annotation_name}, String> {{

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {{
    TODO: Implement validation logic
  }}
}}'''

annotation_test = f'''package {annotation_package};

import static dev.joss.chickpea.utils.ConstraintViolationSetAssert.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class {annotation_name}Test {{

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  // TODO: Fill in values
  public static final String correct = "";
  public static final String incorrect = "";

  @Test
  public void testAnnotatedFieldsCausesExpectedViolation() {{
    {annotation_name}BeanFields {annotation_name}BeanFields = new {annotation_name}BeanFields();

    Set<ConstraintViolation<{annotation_name}BeanFields>> violations = validator.validate({annotation_name}BeanFields);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }}

  @Test
  public void testAnnotatedMethodsCausesExpectedViolation() {{
    {annotation_name}BeanMethods {annotation_name}BeanMethods = new {annotation_name}BeanMethods();

    Set<ConstraintViolation<{annotation_name}BeanMethods>> violations = validator.validate({annotation_name}BeanMethods);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }}

  @Test
  public void testAnnotatedTypesCausesExpectedViolation() {{
    {annotation_name}BeanType {annotation_name}BeanType = new {annotation_name}BeanType();

    Set<ConstraintViolation<{annotation_name}BeanType>> violations = validator.validate({annotation_name}BeanType);

    assertThat(violations).hasSize(1);
    assertThat(violations).containsInvalidValue(incorrect);
  }}

  // TODO: These tests are merely a guideline. You should make sure the tests cover the appropriate functionality!
}}

@Data
class {annotation_name}BeanFields {{

  @{annotation_name} private String correct = {annotation_name}Test.correct;

  @{annotation_name} private String incorrect = {annotation_name}Test.incorrect;
}}

@Data
class {annotation_name}BeanMethods {{

  @{annotation_name}
  private String getCorrect() {{
    return {annotation_name}Test.correct;
  }}

  @{annotation_name}
  private String getIncorrect() {{
    return {annotation_name}Test.incorrect;
  }}
}}

@Data
class {annotation_name}BeanType {{
  private List<@{annotation_name} String> correct = List.of({annotation_name}Test.correct);
  private List<@{annotation_name} String> incorrect = List.of({annotation_name}Test.incorrect);
}}
'''

annotation_interface_path = f'src/main/java/{annotation_package.replace(".", "/")}'
annotation_validator_path = f'{annotation_interface_path}/validator'
annotation_test_path = annotation_interface_path.replace('main', 'test')

create_dirs_if_non_existent(annotation_interface_path, annotation_validator_path, annotation_test_path)

annotation_interface_file_name = f'{annotation_name}.java'
annotation_validator_file_name = f'{annotation_name}Validator.java'
annotation_test_file_name = f'{annotation_name}.java'

with open(f'{annotation_interface_path}/{annotation_interface_file_name}', 'w+') as f:
  f.writelines(annotation_interface)

with open(f'{annotation_validator_path}/{annotation_validator_file_name}', 'w+') as f:
  f.writelines(annotation_validator)

with open(f'{annotation_test_path}/{annotation_test_file_name}', 'w+') as f:
  f.writelines(annotation_test)

print(f'🥳 Successfully Created @{annotation_name}')