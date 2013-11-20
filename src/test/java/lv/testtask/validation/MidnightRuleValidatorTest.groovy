package lv.testtask.validation
import lv.testtask.persistence.domain.Loan
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime
import org.joda.time.DateTimeUtils
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator

class MidnightRuleValidatorTest extends Specification {

    @Shared
    private Validator validator;

    def setupSpec() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }

    def "Can take loan at this time"() {
        def loan = new Loan(amount: Money.of(CurrencyUnit.EUR, 10.1), interest: Money.of(CurrencyUnit.EUR, 10.1), currency: CurrencyUnit.EUR, id: 1,
        returnTill: DateTime.now().plusDays(2), taken: DateTime.now())



        when:
        DateTimeUtils.setCurrentMillisFixed(time.getMillis())
        def validationResult = validator.validate(loan)
        then:
        validationResult != null
        validationResult.size() == 0
        where:
        time << [new DateTime(2013, 10, 11, 23, 0), new DateTime(2013, 10, 11, 7, 0)]
    }

    def "Loan cant be given at this time"() {
        def loan = new Loan(amount: Money.of(CurrencyUnit.EUR, 10.1), interest: Money.of(CurrencyUnit.EUR, 10.1), currency: CurrencyUnit.EUR, id: 1,
                returnTill: DateTime.now().plusDays(2), taken: DateTime.now())



        when:
        DateTimeUtils.setCurrentMillisFixed(time.getMillis())
        def validationResult = validator.validate(loan)
        then:
        validationResult != null
        validationResult.size() == 1
        validationResult.getAt(0).message == "Loan is not allowed at night"
        where:
        time << [new DateTime(2013, 10, 11, 0, 0), new DateTime(2013, 10, 11, 6, 0)]
    }

}