package lv.testtask.gson.converter;

import com.google.gson.*;
import org.joda.money.CurrencyUnit;

import java.lang.reflect.Type;

public class CurrencyConverter implements JsonDeserializer<CurrencyUnit>, JsonSerializer<CurrencyUnit> {
    @Override
    public CurrencyUnit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return CurrencyUnit.getInstance(json.getAsString());
    }

    @Override
    public JsonElement serialize(CurrencyUnit src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getCurrencyCode());
    }
}
