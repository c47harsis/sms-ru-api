package ru.c47harsis.smsruapi.serializer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DefaultSerializer implements RequestSerializer<Object> {
    private final Map<Class<?>, RequestSerializer<?>> serializers = new HashMap<>();

    {
        serializers.put(Boolean.class, new BooleanSerializer());
        serializers.put(LocalDateTime.class, new LocalDateTimeSerializer());
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String serialize(Object value) {
        if (value == null) {
            return null;
        }
        RequestSerializer serializer = serializers.get(value.getClass());
        if (serializer == null) {
            return value.toString();
        }
        return serializer.serialize(value);
    }
}
