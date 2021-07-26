package ru.c47harsis.smsruapi.serializer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeSerializer implements RequestSerializer<LocalDateTime> {

    @Override
    public String serialize(LocalDateTime value) {
        return String.valueOf(value.toEpochSecond(ZoneOffset.UTC));
    }
}
