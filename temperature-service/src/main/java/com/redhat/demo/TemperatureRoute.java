package com.redhat.demo;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TemperatureRoute extends RouteBuilder {

    private final List<TemperatureHumidityEvent> events = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void configure() throws Exception {
        // Ruta para consumir mensajes de Kafka y transformar la temperatura
        from("kafka:{{camel.source.kafka.topic}}")
            .unmarshal().json(TemperatureHumidityEvent.class)
            .process(exchange -> {
                TemperatureHumidityEvent event = exchange.getIn().getBody(TemperatureHumidityEvent.class);
                double fahrenheit = (event.getTemperature() * 9 / 5) + 32;
                event.setTemperature(fahrenheit);
                events.add(event);
            })
            .log("Processed event: ${body}");
    }

    public List<TemperatureHumidityEvent> getEvents() {
        return events;
    }
}
