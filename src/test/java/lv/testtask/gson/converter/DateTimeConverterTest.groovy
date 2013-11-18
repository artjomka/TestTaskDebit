package lv.testtask.gson.converter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import org.joda.time.DateTime
import spock.lang.Specification
import spock.lang.*

import java.lang.reflect.Type


class DateTimeConverterTest extends Specification {

    @Shared
    DateTimeConverter dateTimeConverter

    def setupSpec() {
        dateTimeConverter = new DateTimeConverter()
    }

    def "Convert JSON element to date"() {
        def type = Mock(Type)
        def deserializationContext = Mock(JsonDeserializationContext)
        def jsonElement = new JsonPrimitive("2013-11-18 10:00")

        expect:
        dateTimeConverter.deserialize(jsonElement, type, deserializationContext) == new DateTime(2013, 11, 18, 10, 0)

    }

    def "Convert joda datetime to json element"() {
        def type = Mock(Type)
        def serializationContext = Mock(JsonSerializationContext)
        def datetime = new DateTime(2013, 11, 18, 10, 0)

        expect:
        dateTimeConverter.serialize(datetime,type,serializationContext) == new JsonPrimitive("2013-11-18 10:00")
    }
}