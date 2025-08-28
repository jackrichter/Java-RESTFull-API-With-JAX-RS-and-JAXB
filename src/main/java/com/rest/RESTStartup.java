package com.rest;

import com.rest.dto.Car;
import com.rest.marshall.HashMapWriter;
import com.rest.resources.CarResource;
import com.rest.util.LocalDateTimeParamConverterProvider;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationPath("reststart")
public class RESTStartup extends Application {

//    private static Map<Integer, Car> carDB;

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singeltons = new HashSet<>();
        singeltons.add(new CarResource());

        return singeltons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(HashMapWriter.class);
        set.add(LocalDateTimeParamConverterProvider.class);
        return set;
    }

    public RESTStartup() {
//        carDB = new HashMap<>();
    }

//    public static Map<Integer, Car> getCarDB() {
//        return carDB;
//    }
}