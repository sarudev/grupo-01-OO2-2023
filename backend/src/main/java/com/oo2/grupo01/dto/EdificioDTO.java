package com.oo2.grupo01.dto;

import java.util.List;

import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.entities.Sensores;
import com.oo2.grupo01.mapeos.AulaMapeos;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class EdificioDTO extends GenericDTO<Edificio> {
  private String nombre;
  private Boolean luces;
  private List<AulaDTO> aulas;

  public EdificioDTO(Edificio edificio) {
    super(edificio.getIdLugar(), edificio.getLugar(), edificio.getSensores());
    this.nombre = edificio.getNombre();
    
    this.aulas = AulaMapeos.toDtoList(edificio.getAulas());
    
    this.luces = null;
    
    for (var sensor : sensores) {
      if (sensor.getTipo() == Sensores.TIEMPO && ((SensorTiempo) sensor).isActivo()) {
        this.luces = !((SensorTiempo) sensor).hayLuzSolar();
      }
    }
  }
}
