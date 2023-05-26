package com.oo2.grupo01.dto;

import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.Sensores;
import com.oo2.grupo01.models.SensorTiempo;

public class AulaDTO extends GenericDTO<Aula> {
  private String nombre;
  private Boolean luces;

  public AulaDTO(Aula aula) {
    super(aula.getIdLugar(), aula.getLugar(), aula.getSensores());
    this.nombre = aula.getNombre();

    for (var sensor : sensores) {
      if (sensor.getTipo() == Sensores.TIEMPO && ((SensorTiempo) sensor).isActivo()) {
        this.luces = !((SensorTiempo) sensor).hayLuzSolar();
      }
    }
  }

  public String getNombre() {
    return nombre;
  }

  public Boolean getLuces() {
    return luces;
  }
}
