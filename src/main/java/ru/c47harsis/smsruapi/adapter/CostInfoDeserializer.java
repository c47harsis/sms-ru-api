package ru.c47harsis.smsruapi.adapter;

import com.google.gson.*;
import ru.c47harsis.smsruapi.ApiResponseStatus;
import ru.c47harsis.smsruapi.response.CostResponse;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class CostInfoDeserializer implements JsonDeserializer<Collection<CostResponse.Info>> {

    @Override
    public Collection<CostResponse.Info> deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsJsonObject().entrySet().stream()
                .map(entry -> deserialize(entry, context))
                .collect(Collectors.toList());
    }

    private static CostResponse.Info deserialize(
            Map.Entry<String, JsonElement> entry, JsonDeserializationContext context) {
        String phone = entry.getKey();
        JsonObject json = entry.getValue().getAsJsonObject();

        CostResponse.Info.InfoBuilder builder = CostResponse.Info.builder()
                .phone(phone)
                .status(context.deserialize(json.get("status_code"), ApiResponseStatus.class));

        if (json.get("status_text") != null) builder.statusMessage(json.get("status_text").getAsString());
        if (json.get("cost") != null) builder.cost(json.get("cost").getAsDouble());
        if (json.get("sms") != null) builder.messagesCount(json.get("sms").getAsInt());

        return builder.build();
    }
}
