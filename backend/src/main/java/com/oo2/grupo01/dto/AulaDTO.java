package com.oo2.grupo01.dto;

import com.oo2.grupo01.entities.Aula;

import lombok.Getter;

@Getter
public class AulaDTO extends LugarDTO {
  private String nombre;
  private Boolean luces;
  private Boolean persianas;
  private EdificioDTO lugar;

  public AulaDTO(Aula aula, boolean conLugar) {
    super(aula.getIdLugar(), aula.getNombre(), aula.getTipo(), aula.getSensores(), aula.getHistorial());
    this.nombre = aula.getNombre();

    this.luces = null;
    this.persianas = null;
    if (conLugar)
      this.lugar = new EdificioDTO(aula.getLugar(), false);
  }

  @Override
  public void inicializarVariables() {
    // for (var s : sensores) {
    // if (s.isActivo()) {
    // switch (s.getTipo()) {
    // case CAMARA:
    // SensorCamara sensor = (SensorCamara) Util.convertirSensor(s);
    // luces = persianasAbiertas = sensor.cantPersonas() > 0;

    // break;
    // default:
    // break;

    // }
    // }

    // }

  }

}
