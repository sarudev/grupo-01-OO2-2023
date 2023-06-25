package com.oo2.grupo01.models;

import com.oo2.grupo01.entities.Sensor;

import lombok.Getter;

@Getter
public class SensorBascula extends Sensor {

  private final static double pesoLimite = 1000;
  private double pesoActual;
  private boolean superoLimite;

  public SensorBascula(Sensor sensor) {
    super(sensor);
    pesoActual = pesoActual();
    superoLimite = superoLimite();
  }

  public double pesoActual() {
    return Math.random() < .25
        ? Math.random() * 500 + 1000
        : Math.random() < .25
            ? Math.random() * 50 + 50
            : 0;
  }

  public boolean superoLimite() {
    return pesoActual >= pesoLimite;
  }

  @Override
  public String toString() {
    return "pesoActual=" + pesoActual + ", superoLimite=" + superoLimite;
  }

}
