package com.rest.resources;

import com.rest.RESTStartup;
import com.rest.dto.Car;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars() {
        return Response.ok(RESTStartup.getCarDB()).build();
    }

    @PUT
    @Path("{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateCar(MultivaluedMap<String, String> formParams,
                              @PathParam("carId") Integer carId) {

        Map<Integer, Car> db = RESTStartup.getCarDB();
        Boolean exists = db.containsKey(carId);

        if (!exists) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\": 'Car Id not found'}")
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

    public Response deleteCar(Integer id) {

        return null;
    }
}
