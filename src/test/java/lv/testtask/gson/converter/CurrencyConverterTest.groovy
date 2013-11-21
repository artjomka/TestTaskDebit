package lv.testtask.gson.converter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import org.joda.money.CurrencyUnit
import spock.lang.Shared
import spock.lang.Specification

import java.lang.reflect.Type



class CurrencyConverterTest extends Specification {

    @Shared
    private CurrencyConverter currencyConverter

    def setupSpec() {
        currencyConverter = new CurrencyConverter()
    }

    def "Convert JSON element to currency"() {
        def type = Mock(Type)
        def deserializationContext = Mock(JsonDeserializationContext)
        def jsonElement = new JsonPrimitive("EUR")

        def currency = currencyConverter.deserialize(jsonElement, type, deserializationContext)
        when:
        currency
        then:
        currency != null
        currency == CurrencyUnit.EUR
    }

    def "Convert currency to json element"() {
        def type = Mock(Type)
        def serializationContext = Mock(JsonSerializationContext)
        def currency = CurrencyUnit.EUR;

        def json = currencyConverter.serialize(currency, type, serializationContext)
        when:
        json
        then:
        json != null
        json == new JsonPrimitive("EUR")
    }

}