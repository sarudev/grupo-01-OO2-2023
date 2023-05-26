package com.oo2.grupo01.dto;

import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.entities.Sensores;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class EspacioVerdeDTO extends GenericDTO<EspacioVerde> {
  private String ubicacion;
  private Double humedad;
  private Boolean luces;

  public EspacioVerdeDTO(EspacioVerde espacioVerde) {
    super(espacioVerde.getIdLugar(), espacioVerde.getLugar(), espacioVerde.getSensores());
    this.ubicacion = espacioVerde.getUbicacion();

    for (var sensor : sensores) {
      if (sensor.getTipo() == Sensores.HUMEDAD && ((SensorHumedad) sensor).isActivo()) {
        this.humedad = ((SensorHumedad) sensor).humedad();
      }
      if (sensor.getTipo() == Sensores.TIEMPO && ((SensorTiempo) sensor).isActivo()) {
        this.luces = !((SensorTiempo) sensor).hayLuzSolar();
      }
    }
  }
}
