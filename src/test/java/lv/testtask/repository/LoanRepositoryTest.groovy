package lv.testtask.repository

import lv.testtask.config.MvcConfig
import lv.testtask.persistence.domain.Loan
import lv.testtask.persistence.domain.User
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.AnnotationConfigWebContextLoader
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification



@ContextConfiguration(classes = [MvcConfig], loader = AnnotationConfigWebContextLoader.class)
@ActiveProfiles("dev")
@WebAppConfiguration
class LoanRepositoryTest extends Specification {

    @Autowired
    private LoanRepository loanRepository

    @Transactional
    def "save loan in database"() {
        Loan loan = new Loan(amount: Money.of(CurrencyUnit.EUR, 25.0),
                currency: CurrencyUnit.EUR, interest: Money.of(CurrencyUnit.EUR, 2.0),
                taken: new DateTime(2013,11,19,10,0), returnTill: new DateTime(2013,12,19,10,0))
        loanRepository.saveLoan(loan)
        def id = loan.id
        when:
        def loanResult = loanRepository.getLoanById(id)
        then:
        loanResult != null
        loanResult.taken == new DateTime(2013,11,19,10,0)
        loanResult.returnTill == new DateTime(2013,12,19,10,0)
        loanResult.currency == CurrencyUnit.EUR
        loanResult.amount == Money.of(CurrencyUnit.EUR, 25.0)
        loanResult.interest == Money.of(CurrencyUnit.EUR, 2.0)

    }

    @Transactional
    def "get loans for user"() {
        User user = new User(dateRegistered:  new DateTime(2013,10,19,10,0), mail: "artjom.kalita@gmail.com", password: "mypass")

        Loan firstLoan = new Loan(amount: Money.of(CurrencyUnit.EUR, 25.0),
                currency: CurrencyUnit.EUR, interest: Money.of(CurrencyUnit.EUR, 2.0),
                taken: new DateTime(2013,11,19,10,0), returnTill: new DateTime(2013,12,19,10,0))
        Loan secondLoan = new Loan(amount: Money.of(CurrencyUnit.EUR, 50.0),
                currency: CurrencyUnit.EUR, interest: Money.of(CurrencyUnit.EUR, 5.0),
                taken: new DateTime(2013,11,11,10,0), returnTill: new DateTime(2013,12,11,10,0))
        user.loans = new HashSet<>()
        user.loans.addAll(firstLoan, secondLoan)
        loanRepository.saveUser(user)
        def userId = user.id

        when:
        def userFromDb = loanRepository.getUserById(userId)

        then:
        userFromDb != null
        userFromDb.loans.isEmpty() == false
        userFromDb.loans.size() == 2



    }
}