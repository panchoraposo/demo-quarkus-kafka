package com.redhat.demo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/temperature-humidity")
@Produces(MediaType.APPLICATION_JSON)
public class TemperatureHumidityResource {

    @Inject
    TemperatureHumidityConsumer consumer;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemperatureHumidity() {
        TemperatureHumidityEvent event = consumer.getLastEvent();
        
        if (event != null) {
            return Response.ok(event).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}