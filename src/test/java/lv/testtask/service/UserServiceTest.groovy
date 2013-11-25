package lv.testtask.service
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import lv.testtask.gson.converter.CurrencyConverter
import lv.testtask.gson.converter.DateTimeConverter
import lv.testtask.gson.converter.MoneyConverter
import lv.testtask.repository.MainRepository
import lv.testtask.service.util.ValidationHelper
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validator

class UserServiceTest extends Specification {

    @Shared
    private Gson gson;


    def setupSpec() {
        gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeConverter())
                .registerTypeAdapter(Money.class, new MoneyConverter())
                .registerTypeAdapter(CurrencyUnit.class, new CurrencyConverter())
                .create();
    }


    def "Register user without errors"() {

        def userService = new UserServiceImpl()
        Validator validator = Mock()
        validator.validate(_) >> new HashSet<ConstraintViolation<java.lang.Object>>()
        MainRepository loanRepository = Mock()
        ValidationHelper validationHelper = new ValidationHelper()
        validationHelper.validator = validator


        userService.validationHelper =  validationHelper
        userService.mainRepository = loanRepository
        userService.gson = gson


        def userString = "{\"username\":\"23232323\",\"email\":\"artjom.kalita@gmail.com\",\"password\":\"mypass\"}"
        def result = userService.registerUser(userString)
        when:
        result

        then:
        result != null
        result.contains("status")
        result.contains("SUCCESS")



    }

}