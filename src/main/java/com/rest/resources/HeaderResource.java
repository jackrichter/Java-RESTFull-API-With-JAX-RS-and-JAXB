package com.rest.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("headerinfo")
public class HeaderResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHeadersInfo(@Context HttpHeaders headers) {
        return Response.ok(headers.getRequestHeaders()).build();
    }
}
