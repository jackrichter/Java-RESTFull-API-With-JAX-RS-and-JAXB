package com.rest.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("resttest")
public class TestREST {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String test() {
        return "<html><body><h1>Test Successful!</h1></body></html>";
    }
}
