package lv.testtask.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import lv.testtask.gson.converter.CurrencyConverter
import lv.testtask.gson.converter.DateTimeConverter
import lv.testtask.gson.converter.MoneyConverter
import lv.testtask.persistence.domain.Loan
import lv.testtask.persistence.domain.User
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime
import spock.lang.Shared
import spock.lang.Specification

import static org.joda.money.CurrencyUnit.EUR

class JsonObjectConversion extends Specification {

    @Shared
    private Gson gson;


    def setupSpec() {
        gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeConverter())
                .registerTypeAdapter(Money.class, new MoneyConverter())
                .registerTypeAdapter(CurrencyUnit.class, new CurrencyConverter())
                .create();
    }

    def "Convert user object to JSON string"() {
        def user = new User(dateRegistered: new DateTime(2013, 10, 10, 8, 52), id: 1, email: "artjom.kalita@gmail.com", username: "23232323", password: "mypass")
        user.loans = new HashSet<>()
        user.loans << new Loan(amount: Money.of(EUR, 13.2), interest: Money.of(EUR, 2.1), currency: EUR, id: 2, returnTill: new DateTime(2013, 10, 10, 8, 52), taken: new DateTime(2013, 10, 10, 8, 52))

        def json = gson.toJson(user)
        when:
        json
        then:
        json != null
        json == "{\"id\":1,\"username\":\"23232323\",\"email\":\"artjom.kalita@gmail.com\",\"password\":\"mypass\",\"dateRegistered\":\"2013-10-10 08:52\",\"loans\":[{\"id\":2," +
                "\"taken\":\"2013-10-10 08:52\",\"returnTill\":\"2013-10-10 08:52\",\"amount\":\"13.2\",\"interest\":\"2.1\",\"currency\":\"EUR\"}]}"
    }

    def "Convert Json to user object"() {
        def json = "{\"id\":1,\"username\":\"23232323\",\"email\":\"artjom.kalita@gmail.com\",\"password\":\"mypass\",\"dateRegistered\":\"2013-10-10 08:52\",\"loans\":[{\"id\":2," +
                "\"taken\":\"2013-10-10 08:52\",\"returnTill\":\"2013-10-10 08:52\",\"amount\":\"13.2\",\"interest\":\"2.1\",\"currency\":\"EUR\"}]}"

        def user = gson.fromJson(json, User.class)
        when:
        user
        then:
        user != null
        user.id == 1
        user.dateRegistered == new DateTime(2013, 10, 10, 8, 52)
        user.email == "artjom.kalita@gmail.com"
        user.username == "23232323"
        user.password == "mypass"

        user.loans != null
        user.loans.size() == 1
        def loan = user.loans.asList().get(0)
        loan != null
        loan.id == 2
        loan.taken == new DateTime(2013, 10, 10, 8, 52)
        loan.returnTill == new DateTime(2013, 10, 10, 8, 52)
        loan.amount == Money.of(EUR, 13.2)
        loan.interest == Money.of(EUR, 2.1)
        loan.currency == EUR

    }
}