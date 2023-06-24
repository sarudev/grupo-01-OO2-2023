package com.oo2.grupo01.dto;

import com.oo2.grupo01.entities.Estacionamiento;

import lombok.Getter;

@Getter
public class EstacionamientoDTO extends LugarDTO {
  private Boolean libre;
  private ParkingDTO lugar;

  public EstacionamientoDTO(Estacionamiento est, boolean conParking) {
    super(est.getIdLugar(), est.getNombre(), est.getTipo(), est.getSensores(), est.getHistorial());

    this.libre = null;

    if (conParking)
      this.lugar = new ParkingDTO(est.getLugar(), false);
  }

  @Override
  public void inicializarVariables() {
    // for (var sensor : sensores) {
    // if (sensor.isActivo()) {
    // switch (sensor.getTipo()) {
    // case BASCULA:
    // SensorBascula sensorBascula = (SensorBascula) Util.convertirSensor(sensor);
    // ocupado = sensorBascula.superoLimite();
    // break;
    // default:
    // break;

    // }
    // }
    // }
  }

}
