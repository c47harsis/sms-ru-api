package ru.c47harsis.smsruapi.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ru.c47harsis.smsruapi.response.BlacklistAllResponse;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class BlacklistInfoDeserializer implements JsonDeserializer<Collection<BlacklistAllResponse.Info>> {

    @Override
    public Collection<BlacklistAllResponse.Info> deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsJsonObject().entrySet().stream()
                .map(BlacklistInfoDeserializer::deserialize)
                .collect(Collectors.toList());
    }

    private static BlacklistAllResponse.Info deserialize(Map.Entry<String, JsonElement> entry) {
        BlacklistAllResponse.Info.InfoBuilder builder = BlacklistAllResponse.Info.builder().phone(entry.getKey());
        if (entry.getValue() != null) builder.reason(entry.getValue().getAsString());
        return builder.build();
    }
}