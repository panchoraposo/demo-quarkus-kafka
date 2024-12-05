package com.redhat.demo;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import java.util.Random;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.demo.event.TemperatureHumidityEvent;

import io.quarkus.scheduler.Scheduled;

import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class TemperatureHumidityProducer {

    private static final Logger LOG = Logger.getLogger(TemperatureHumidityProducer.class);

    private Random random = new Random();
    
    @Inject
    @Channel("temperature-humidity")
    Emitter<String> emitter;  // El emisor para enviar mensajes a Kafka

    @Scheduled(every = "1s")
    public void generateAndSend() {
        // Generar datos aleatorios de temperatura y humedad
        double temperature = 15 + random.nextDouble() * 10; // Temperatura entre 15°C y 25°C
        double humidity = 30 + random.nextDouble() * 40; // Humedad entre 30% y 70%

        // Crear el evento
        TemperatureHumidityEvent event = new TemperatureHumidityEvent(temperature, humidity);

        // Convertir a JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonEvent = objectMapper.writeValueAsString(event);

            // Enviar el evento al canal (Kafka)
            emitter.send(jsonEvent);  // Enviar el mensaje a Kafka

            LOG.info("Event sent to Kafka: " + jsonEvent);
        } catch (Exception e) {
            LOG.error("Error serializando el evento", e);
        }
    }

    // Método para simular el envío de eventos periódicamente (cada 3 segundos)
    public void startSendingEvents() {
        new Thread(() -> {
            try {
                while (true) {
                    generateAndSend();
                    TimeUnit.SECONDS.sleep(3); // Espera 3 segundos entre eventos
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}