package lv.testtask.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import lv.testtask.LoanStatus
import lv.testtask.config.MvcConfig
import lv.testtask.gson.converter.CurrencyConverter
import lv.testtask.gson.converter.DateTimeConverter
import lv.testtask.gson.converter.MoneyConverter
import lv.testtask.persistence.domain.User
import lv.testtask.repository.MainRepository
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.AnnotationConfigWebContextLoader
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared
import spock.lang.Specification

@ContextConfiguration(classes = [MvcConfig], loader = AnnotationConfigWebContextLoader.class)
@ActiveProfiles("dev")
@WebAppConfiguration
@Transactional
class LoanIntegrationServiceTest extends Specification {

    @Autowired
    private MainRepository mainRepository

    @Autowired
    private LoanService loanService

    @Shared
    private Gson gson


    def setupSpec() {
        gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeConverter())
                .registerTypeAdapter(Money.class, new MoneyConverter())
                .registerTypeAdapter(CurrencyUnit.class, new CurrencyConverter())
                .create();
    }

    def "successfully take loan"() {
        def user = new User(dateRegistered: DateTime.now(), email: "artjom.kalita@gmail.com", phone: "23232323", password: "mypass")
        mainRepository.saveUser(user)
        def loanData = "{\"taken\":\"2013-10-10 13:40\",\"returnTill\":\"2013-12-10 08:52\",\"amount\":\"100.2\",\"interest\":\"10.1\",\"currency\":\"EUR\"}"
        def result = loanService.takeLoan(loanData, "127.0.0.1", user)
        when:
        result
        then :
        result != null
        result == "{\"status\":\"SUCCESS\",\"errorData\":[]}"

        def userFromDb = mainRepository.getUserByPhoneAndMail("23232323", "artjom.kalita@gmail.com")
        when:
        userFromDb
        then:
        userFromDb.loans != null
        userFromDb.loans.size() == 1

        def loan = userFromDb.loans.getAt(0)
        when:
        loan
        then:
        loan != null
        loan.status == LoanStatus.ONGOING.getValue()
        loan.id != null
        loan.taken == new DateTime(2013,10,10,13,40)
        loan.returnTill == new DateTime(2013,12,10,8,52)
        loan.amount == Money.of(CurrencyUnit.EUR, 100.2)
        loan.interest == Money.of(CurrencyUnit.EUR, 10.1)

    }

}