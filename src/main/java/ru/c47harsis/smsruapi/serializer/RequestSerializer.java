package ru.c47harsis.smsruapi.serializer;

@FunctionalInterface
public interface RequestSerializer<T> {

    String serialize(T value);
}
