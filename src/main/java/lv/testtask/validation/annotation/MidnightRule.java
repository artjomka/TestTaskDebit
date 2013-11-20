package lv.testtask.validation.annotation;

import lv.testtask.validation.validator.MidnightRuleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = MidnightRuleValidator.class)
public @interface MidnightRule {

    String message() default "Loan is not allowed at night";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
