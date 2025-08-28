package com.rest.util;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Provider
public class LocalDateTimeParamConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

        if (rawType.equals(LocalDateTime.class)) {
            return (ParamConverter<T>) new ParamConverter<LocalDateTime>() {

                private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                @Override
                public LocalDateTime fromString(String value) {
                    return value != null && !value.isEmpty() ? LocalDateTime.from(formatter.parse(value)) : null;
                }

                @Override
                public String toString(LocalDateTime value) {
                    return value!= null ? formatter.format(value) : null;
                }
            };
        } else {
            return null;
        }
    }
}
