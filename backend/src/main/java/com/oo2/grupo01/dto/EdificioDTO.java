package com.oo2.grupo01.dto;

import java.util.ArrayList;
import java.util.List;

import com.oo2.grupo01.entities.Edificio;

import lombok.Getter;

@Getter
public class EdificioDTO extends LugarDTO {
  private Boolean luces;
  private List<AulaDTO> aulas = new ArrayList<>();

  public EdificioDTO(Edificio edif, boolean conAulas) {
    super(edif.getIdLugar(), edif.getNombre(), edif.getTipo(), edif.getSensores(),
        edif.getHistorial());
    if (conAulas)
      this.aulas = edif.getAulas().stream().map(au -> new AulaDTO(au, false)).toList();
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
