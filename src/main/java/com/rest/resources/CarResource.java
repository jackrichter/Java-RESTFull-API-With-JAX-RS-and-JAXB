package com.rest.resources;

import com.rest.RESTStartup;
import com.rest.dto.Car;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Path("car")
public class CarResource {

//    private HashMap<Integer, Car> carDB = new HashMap<>();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addCar(@FormParam("licenceplate") String licenceplate,
                           @FormParam("color") String color) {

        Map<Integer, Car> db = RESTStartup.getCarDB();

        Double id = Math.random() * 1000 + 1;

        Car car = new Car(licenceplate, color);

        db.put(id.intValue(), car);

        return Response.ok(db.get(id.intValue())).build();
    }
}
