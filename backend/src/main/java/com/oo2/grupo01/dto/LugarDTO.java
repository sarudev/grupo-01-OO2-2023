package com.oo2.grupo01.dto;

import java.util.HashSet;
import java.util.Set;

import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.enums.Lugares;
import com.oo2.grupo01.models.SensorBascula;
import com.oo2.grupo01.models.SensorCamara;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTemperatura;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public abstract class LugarDTO {
  protected Long id;
  protected Lugares tipo;
  protected String nombre;
  protected Set<Sensor> sensores = new HashSet<>();
  protected Set<Historial> historial = new HashSet<>();

  protected LugarDTO(Long id, String nombre, Lugares tipo, Set<Sensor> sensores, Set<Historial> historial) {
    this.id = id;
    this.nombre = nombre;
    this.tipo = tipo;
    this.historial = historial;

    for (var sensor : sensores) {
      switch (sensor.getTipo()) {
        case bascula:
          this.sensores.add(new SensorBascula(sensor));
          break;
        case camara:
          this.sensores.add(new SensorCamara(sensor));
          break;
        case humedad:
          this.sensores.add(new SensorHumedad(sensor));
          break;
        case tiempo:
          this.sensores.add(new SensorTiempo(sensor));
          break;
        case temperatura:
          this.sensores.add(new SensorTemperatura(sensor));
      }
    }

  }

  public abstract void inicializarVariables();
}
