package lv.testtask.validation

import lv.testtask.persistence.domain.User
import org.joda.time.DateTime
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator



class UserValidation extends Specification {

    @Shared
    private Validator validator;

    def setupSpec() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }

    def "User fields not emtpy validation"() {
        def user = new User(id: 1, dateRegistered: DateTime.now(), password: "", phone: "", email: "")
        def validationResult = validator.validate(user)
        def expectedFields = ["email", "phone", "password"]
        expect:
        validationResult != null
        validationResult.size() == 3
        validationResult.each {result -> expectedFields.contains(result.propertyPath.toString()) && result.message == "may not be empty"}
    }

    def "Check user mail correctness" () {
        def user = new User(id: 1, dateRegistered: DateTime.now(), password: "123", phone: "123", email: "aaaaa")
        def validationResult = validator.validate(user)

        expect:
        validationResult != null
        validationResult.size() == 1
        validationResult.getAt(0).message == "not a well-formed email address"

    }
    def "User correct data"() {
        def user = new User(id: 1, dateRegistered: DateTime.now(), password: "pass", phone: "phone", email: "artjom.kalita@gmail.com")
        def validationResult = validator.validate(user)

        expect:
        validationResult != null
        validationResult.size() == 0
    }
}