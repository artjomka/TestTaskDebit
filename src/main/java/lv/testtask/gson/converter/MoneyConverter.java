package lv.testtask.gson.converter;

import com.google.gson.*;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.lang.reflect.Type;

public class MoneyConverter implements JsonDeserializer<Money>, JsonSerializer<Money> {
    @Override
    public Money deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Double value = json.getAsDouble();
        return Money.of(CurrencyUnit.EUR, value);
    }

    @Override
    public JsonElement serialize(Money src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(String.valueOf(src.getAmount().doubleValue()));
    }
}
