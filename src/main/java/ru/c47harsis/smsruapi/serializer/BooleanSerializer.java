package ru.c47harsis.smsruapi.serializer;

public class BooleanSerializer implements RequestSerializer<Boolean> {

    @Override
    public String serialize(Boolean value) {
        return value ? "1" : "0";
    }
}
