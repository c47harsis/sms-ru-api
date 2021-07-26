package ru.c47harsis.smsruapi.serializer;

import ru.c47harsis.smsruapi.serializer.RequestSerializer;
import ru.c47harsis.smsruapi.serializer.DefaultSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Param {

    String value();

    Class<? extends RequestSerializer<?>> serializer() default DefaultSerializer.class;
}
