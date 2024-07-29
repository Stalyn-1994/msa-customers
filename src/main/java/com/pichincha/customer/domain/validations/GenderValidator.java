package com.pichincha.customer.domain.validations;


import com.pichincha.customer.domain.enums.GenderEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<GenderValidation, GenderEnum> {


  @Override
  public void initialize(GenderValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(GenderEnum value, ConstraintValidatorContext context) {
    return GenderEnum.findByCode(value.name()).isPresent();
  }
}