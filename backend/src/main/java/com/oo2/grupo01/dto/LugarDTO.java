package com.oo2.grupo01.dto;

import java.util.Set;

import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.enums.Lugares;
import com.oo2.grupo01.models.SensorBascula;
import com.oo2.grupo01.models.SensorCamara;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTemperatura;
import com.oo2.grupo01.models.SensorTiempo;

public abstract class LugarDTO {
  protected Long id;
  protected Lugares tipo;
  protected String nombre;
  protected Set<Sensor> sensores;
  protected Set<Historial> historial;

  protected LugarDTO(Long id, String nombre, Lugares tipo, Set<Sensor> sensores, Set<Historial> historial) {
    this.id = id;
    this.nombre = nombre;
    this.tipo = tipo;
    this.historial = historial;

    for (var sensor : sensores) {
      switch (sensor.getTipo()) {
        case BASCULA:
          this.sensores.add(new SensorBascula(sensor));
          break;
        case CAMARA:
          this.sensores.add(new SensorCamara(sensor));
          break;
        case HUMEDAD:
          this.sensores.add(new SensorHumedad(sensor));
          break;
        case TIEMPO:
          this.sensores.add(new SensorTiempo(sensor));
          break;
        case TEMPERATURA:
          this.sensores.add(new SensorTemperatura(sensor));
      }
    }

  }

  public abstract void inicializarVariables();
}
