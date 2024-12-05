package com.redhat.demo.event;

public class TemperatureHumidityEvent {
    private double temperature;
    private double humidity;

    // Constructor, Getters y Setters
    public TemperatureHumidityEvent(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

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
}