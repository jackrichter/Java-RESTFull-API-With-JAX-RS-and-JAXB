package com.rest;

import com.rest.dto.Car;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashMap;
import java.util.Map;

@ApplicationPath("reststart")
public class RESTStartup extends Application {

    private static Map<Integer, Car> carDB;

    public RESTStartup() {
        carDB = new HashMap<>();
    }

    public static Map<Integer, Car> getCarDB() {
        return carDB;
    }
}