package com.rest.resources;

import com.rest.RESTStartup;
import com.rest.dto.Car;
import com.rest.marshall.CarDBWrapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Path("car")
public class CarResource {

    private HashMap<Integer, Car> carDB = new HashMap<>();

    private final String MSG = "{\"message\": \"The following cars weren't able to be added, their keys already exist: ";

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addCar(@FormParam("licenceplate") String licenceplate,
                           @FormParam("color") String color) {

//        Map<Integer, Car> db = RESTStartup.getCarDB();
        Map<Integer, Car> db = carDB;

        Double id = Math.random() * 1000 + 1;

        Car car = new Car(licenceplate, color);

        db.put(id.intValue(), car);

        return Response.ok(db.get(id.intValue())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Response addCar(CarDBWrapper cars) {

        StringBuilder sb = new StringBuilder(MSG);

        cars.getCars().forEach((k, v) -> {
            if (carDB.containsKey(k)) {
                sb.append(k).append(",");
            } else {
                carDB.put(k, v);
            }
        });

        if (sb.toString().matches(".*\\d+.*")) {
            sb.append("\"}");
            return Response.ok(sb.toString()).build();
        }

        return Response.ok("{\"message\": \"Cars successfully added\"}").build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCars() {
//        return Response.ok(RESTStartup.getCarDB()).build();
        return Response.ok(carDB).build();
    }

    @PUT
    @Path("{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateCar(MultivaluedMap<String, String> formParams,
                              @PathParam("carId") Integer carId) {

//        Map<Integer, Car> db = RESTStartup.getCarDB();
        Map<Integer, Car> db = carDB;
        Boolean exists = db.containsKey(carId);

        if (!exists) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\": \"Car Id not found\"}")
                    .build();
        }

        String licenceplate = formParams.getFirst("licenceplate");
        String color = formParams.getFirst("color");

        Car car = db.get(carId);

        if ((licenceplate != null)) {
            car.setLicencePlate(licenceplate);
        }
        if ((color != null)) {
            car.setColor(color);
        }

        db.replace(carId, car);

        return Response.ok(car).build();
    }

    @DELETE
    @Path("{carId}")
    public Response deleteCar(@PathParam("carId") Integer carId) {

//        Map<Integer, Car> db = RESTStartup.getCarDB();
        Map<Integer, Car> db = carDB;
        boolean exists = db.containsKey(carId);

        if (!exists) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\": \"Car Id not found\"}")
                    .build();
        }

        db.remove(carId);

        return Response.status(Response.Status.OK)
                .entity("{\"message\": \"Car deleted successfully\"}")
                .build();
    }
}
