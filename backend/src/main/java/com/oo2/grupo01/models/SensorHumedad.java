package com.oo2.grupo01.models;

import com.oo2.grupo01.entities.Sensor;

import lombok.Getter;

@Getter
public class SensorHumedad extends Sensor {

  private double humedad;

  public SensorHumedad(Sensor sensor) {
    super(sensor);
    humedad = humedad();
  }

  public double humedad() {
    return Math.random() % 75 + 25;
  }

  @Override
  public String toString() {
    return "humedad=" + humedad;
  }
}
