package lv.testtask.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import lv.testtask.gson.converter.CurrencyConverter
import lv.testtask.gson.converter.DateTimeConverter
import lv.testtask.gson.converter.MoneyConverter
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.joda.time.DateTime
import spock.lang.Shared
import spock.lang.Specification



class LoanServiceTest extends Specification {

    @Shared
    private Gson gson;


    def setupSpec() {
        gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeConverter())
                .registerTypeAdapter(Money.class, new MoneyConverter())
                .registerTypeAdapter(CurrencyUnit.class, new CurrencyConverter())
                .create();
    }



}