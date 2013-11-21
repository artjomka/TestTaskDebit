package lv.testtask.gson.converter
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import org.joda.money.Money
import spock.lang.Shared
import spock.lang.Specification

import java.lang.reflect.Type

import static org.joda.money.CurrencyUnit.EUR



class MoneyConverterTest extends Specification {

    @Shared
    private MoneyConverter moneyConverter

    def setupSpec(){
        moneyConverter = new MoneyConverter()
    }

    def "convert Json element to Money"(){
        def jsonElement = new JsonPrimitive(12.1)
        def type = Mock(Type)
        def deserializationContext = Mock(JsonDeserializationContext)

        def money = moneyConverter.deserialize(jsonElement, type, deserializationContext)
        when:
        money
        then:
        money != null
        money.getAmount() != null
        money.getAmount().doubleValue() == 12.1
    }


    def "convert Money to Json "(){
        def type = Mock(Type)
        def serializationContext = Mock(JsonSerializationContext)
        Money money = Money.of(EUR, 13.2)

        def json = moneyConverter.serialize(money, type, serializationContext)
        when:
        json
        then:
        json != null
        json == new JsonPrimitive(String.valueOf(13.2))
    }
}