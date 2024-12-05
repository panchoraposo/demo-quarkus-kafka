package com.redhat.demo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/temperature")
@Produces(MediaType.APPLICATION_JSON)
public class TemperatureResource {

    @Inject
    TemperatureRoute temperatureRoute;

    @GET
    public List<TemperatureHumidityEvent> getTemperatureEvents() {
        return temperatureRoute.getEvents();
    }
    
}