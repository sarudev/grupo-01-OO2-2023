package com.oo2.grupo01.dto;

import java.util.List;

import com.oo2.grupo01.entities.Edificio;

import lombok.Getter;

@Getter
public class EdificioDTO extends LugarDTO {
  private Boolean luces;
  private List<AulaDTO> aulas;

  public EdificioDTO(Edificio edif) {
    super(edif.getIdLugar(), edif.getNombre(), edif.getTipo(), edif.getSensores(),
        edif.getHistorial());
    System.out.println(edif);
    this.aulas = edif.getAulas().stream().map(au -> new AulaDTO(au)).toList();
    this.luces = null;
  }

  @Override
  public void inicializarVariables() {
    // for (var sensor : sensores) {
    // if (sensor.isActivo()) {

    // switch (sensor.getTipo()) {
    // case TIEMPO:
    // SensorTiempo s = (SensorTiempo) Util.convertirSensor(sensor);
    // luces = s.hayLuzSolar();
    // break;

    // default:
    // break;

    // }

    // }
    // }
  }

}
