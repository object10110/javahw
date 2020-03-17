package org.itstep.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class AgeRangeValidator implements ConstraintValidator<AgeRange, Integer> {
   AgeRange constraint;

   public void initialize(AgeRange constraint) {
      this.constraint = constraint;
   }

   public boolean isValid(Integer age, ConstraintValidatorContext context) {
      boolean valid = false;
      try {
         valid = age >= constraint.min() && age <= constraint.max();
      } catch (Throwable ex) {
         log.error(ex.getLocalizedMessage(), ex);
      }
      return valid;
   }
}
