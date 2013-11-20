package lv.testtask.validation.validator;

import lv.testtask.persistence.domain.Loan;
import lv.testtask.validation.annotation.MidnightRule;
import org.joda.time.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MidnightRuleValidator implements ConstraintValidator<MidnightRule, Loan> {

    @Override
    public void initialize(MidnightRule constraintAnnotation) {

    }

    @Override
    public boolean isValid(Loan value, ConstraintValidatorContext context) {
        final DateTime now = DateTime.now();
        return now.getHourOfDay() > 6;
    }


}
