package lv.testtask.validation.validator;

import lv.testtask.validation.annotation.IpRestrictionRule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IpRestrictionRuleValidator implements ConstraintValidator<IpRestrictionRule, Integer> {

    private Integer maxLoanAmountInDaytime;


    @Override
    public void initialize(IpRestrictionRule constraintAnnotation) {
        maxLoanAmountInDaytime = constraintAnnotation.maxLoanAmountInDaytime();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value <= maxLoanAmountInDaytime;
    }
}
