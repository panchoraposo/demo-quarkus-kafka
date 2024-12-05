package com.redhat.demo;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class TemperatureHumidityConsumer {

    private static final Logger LOG = Logger.getLogger(TemperatureHumidityConsumer.class);

    private TemperatureHumidityEvent lastEvent; // Almacena el último evento recibido

    @Incoming("temperature-humidity")
    public void consume(String payload) {
        try {
            // Deserializar el JSON en un objeto TemperatureHumidityEvent
            ObjectMapper objectMapper = new ObjectMapper();
            TemperatureHumidityEvent event = objectMapper.readValue(payload, TemperatureHumidityEvent.class);
            
            LOG.infof("Received event: temperature=%f, humidity=%f", event.getTemperature(), event.getHumidity());
            
            // Actualizar el último evento recibido
            lastEvent = event;
        } catch (Exception e) {
            LOG.error("Error parsing message", e);
        }
    }

    public TemperatureHumidityEvent getLastEvent() {
        return lastEvent;
    }
    
}