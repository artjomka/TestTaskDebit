package lv.testtask.validation

import org.joda.time.DateTime
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator



class IpRestrictionRuleValidatorTest extends Specification {
    @Shared
    private Validator validator;

    def setupSpec() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }
    
    def "Maximum loan in day time reached"(){
        def ipRestriction = new IpRestrictionData(lastLoanTaken: DateTime.now(), loansTakenToday: 4)

        def validate = validator.validate(ipRestriction)
        when:
        validate
        then:
        validate.size() == 1
        def validationElem = validate.getAt(0)
        validationElem.propertyPath.toString() == "loansTakenToday"
        validationElem.message == "Maximum allowed loans from this ip address reached"

    }
    def "Loan can be taken"(){
        def ipRestriction = new IpRestrictionData(lastLoanTaken: DateTime.now(), loansTakenToday: 3)

        def validate = validator.validate(ipRestriction)
        when:
        validate
        then:
        validate.size() == 0
    }
}