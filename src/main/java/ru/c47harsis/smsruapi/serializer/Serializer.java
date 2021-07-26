package ru.c47harsis.smsruapi.serializer;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

@UtilityClass
public class Serializer {

    public Map<String, String> serialize(Object obj) {
        Map<String, String> result = new LinkedHashMap<>();
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            result.putAll(fillParams(clazz, obj));
            clazz = clazz.getSuperclass();
        }
        return result;
    }

    private Method getReadMethod(Class<?> clazz, String propertyName) {
        for (Method method : clazz.getDeclaredMethods()) {
            String prop = Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
            if (("is" + prop).equals(method.getName()) || ("get" + prop).equals(method.getName())) {
                return method;
            }
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Map<String, String> fillParams(Class<?> clazz, Object obj) {
        Map<String, String> result = new LinkedHashMap<>();
        try {
            for (Field field : clazz.getDeclaredFields()) {
                Param param = field.getAnnotation(Param.class);
                Method getter = getReadMethod(clazz, field.getName());
                if (getter != null) {
                    Param paramGetter = getter.getAnnotation(Param.class);
                    if (paramGetter != null) {
                        param = paramGetter;
                    }
                }
                if (param == null) {
                    continue;
                }
                Object value = getter != null ? getter.invoke(obj) : field.get(obj);
                if (value == null) {
                    continue;
                }
                RequestSerializer serializer = param.serializer()
                        .getConstructor()
                        .newInstance();
                value = serializer.serialize(value);
                if (value == null) {
                    continue;
                }
                result.put(param.value(), (String) value);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            // TODO: handle
            throw new RuntimeException(e);
        }
        return result;
    }
}
