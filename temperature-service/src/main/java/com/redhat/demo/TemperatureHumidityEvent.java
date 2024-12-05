package com.redhat.demo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemperatureHumidityEvent {
    
    private double temperature;
    private double humidity;

    // Constructor por defecto (necesario para la deserialización)
    public TemperatureHumidityEvent() {
    }

    // Constructor parametrizado (opcional, pero útil para pruebas o inicialización)
    public TemperatureHumidityEvent(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Getters y Setters
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "TemperatureHumidityEvent{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }

}