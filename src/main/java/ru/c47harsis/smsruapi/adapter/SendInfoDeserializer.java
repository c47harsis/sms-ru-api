package ru.c47harsis.smsruapi.adapter;

import com.google.gson.*;
import ru.c47harsis.smsruapi.ApiResponseStatus;
import ru.c47harsis.smsruapi.response.SendResponse;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class SendInfoDeserializer implements JsonDeserializer<Collection<SendResponse.Info>> {

    @Override
    public Collection<SendResponse.Info> deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsJsonObject().entrySet().stream()
                .map(entry -> deserialize(entry, context))
                .collect(Collectors.toList());
    }

    private static SendResponse.Info deserialize(
            Map.Entry<String, JsonElement> entry, JsonDeserializationContext context) {
        String phone = entry.getKey();
        JsonObject json = entry.getValue().getAsJsonObject();

        SendResponse.Info.InfoBuilder builder = SendResponse.Info.builder()
                .phone(phone)
                .status(context.deserialize(json.get("status_code"), ApiResponseStatus.class));

        if (json.get("status_text") != null) builder.statusMessage(json.get("status_text").getAsString());
        if (json.get("sms_id") != null) builder.messageId(json.get("sms_id").getAsString());

        return builder.build();
    }
}