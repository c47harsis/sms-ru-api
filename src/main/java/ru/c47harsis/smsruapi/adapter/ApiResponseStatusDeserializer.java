package ru.c47harsis.smsruapi.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ru.c47harsis.smsruapi.ApiResponseStatus;

import java.lang.reflect.Type;

public class ApiResponseStatusDeserializer implements JsonDeserializer<ApiResponseStatus> {

    @Override
    public ApiResponseStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ApiResponseStatus status = ApiResponseStatus.of(json.getAsInt());
        if (status == null) {
            throw new JsonParseException("Unfamiliar API response status: " + json.getAsInt());
        }
        return status;
    }
}
