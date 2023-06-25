package com.oo2.grupo01.models;

import com.oo2.grupo01.entities.Sensor;

public class SensorTemperatura extends Sensor {
  public SensorTemperatura(Sensor sensor) {
    super(sensor);
  }

  public double temperatura() {
    return Math.round(Math.random() * 50);
  }

  @Override
  public String toString() {
    return "temperatura=" + temperatura();
  }
}
