package com.oo2.grupo01.dto;

import java.util.ArrayList;
import java.util.List;

import com.oo2.grupo01.Utils.SensorUtil;
import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.enums.Lugares;
import com.oo2.grupo01.entities.enums.Sensores;

import lombok.Getter;

@Getter
public abstract class LugarDTO {
  protected Long id;
  protected Lugares tipo;
  protected String nombre;
  protected List<Sensor> sensores = new ArrayList<>();
  protected List<Historial> historial = new ArrayList<>();

  protected LugarDTO(Long id, String nombre, Lugares tipo, List<Sensor> sensores, List<Historial> historial) {
    this.id = id;
    this.nombre = nombre;
    this.tipo = tipo;
    this.historial = historial;

    for (var sensor : sensores) {
      var sen = SensorUtil.convertirSensor(sensor);

      if (sen != null)
        this.sensores.add(sen);
    }

  }

  public abstract void inicializarVariables(Lugar lugar, Util.SaveHistorial lambda);

  protected List<Historial> getBy(Sensores tipo) {
    return historial.stream().filter(h -> h.getTipo().equals(tipo)).toList();
  }
}
