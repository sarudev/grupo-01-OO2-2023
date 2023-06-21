package com.oo2.grupo01.dto;

import com.oo2.grupo01.entities.EspacioVerde;

import lombok.Getter;

@Getter
public class EspacioVerdeDTO extends LugarDTO {
  private Boolean luces;
  private Boolean aspersores;

  public EspacioVerdeDTO(EspacioVerde esp) {
    super(esp.getIdLugar(), esp.getNombre(), esp.getTipo(), esp.getSensores(), esp.getHistorial());

    this.luces = null;
    this.aspersores = null;
  }

  @Override
  public void inicializarVariables() {
    // for (var sensor : sensores) {
    // if (sensor.isActivo()) {
    // switch (sensor.getTipo()) {
    // case HUMEDAD:
    // SensorHumedad sensorHumedad = (SensorHumedad) Util.convertirSensor(sensor);

    // humedad = sensorHumedad.humedad();
    // aspersoresEncendidos = humedad < 50;

    // break;
    // case TIEMPO:
    // SensorTiempo sensorTiempo = (SensorTiempo) Util.convertirSensor(sensor);
    // luces = sensorTiempo.hayLuzSolar();
    // break;
    // default:
    // break;

    // }
    // }
    // }
  }

}
