package com.oo2.grupo01.dto;

import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.entities.Sensores;
import com.oo2.grupo01.models.SensorBascula;

public class EstacionamientoDTO extends GenericDTO<Estacionamiento> {
  Integer numero;
  Boolean ocupado;

  public EstacionamientoDTO(Estacionamiento est) {
    super(est.getIdLugar(), est.getLugar(), est.getSensores());
    this.numero = est.getNumero();

    for (var sensor : sensores) {
      if (sensor.getTipo() == Sensores.BASCULA && ((SensorBascula) sensor).isActivo()) {
        this.ocupado = ((SensorBascula) sensor).superoLimite();
      }
    }
  }

  public Integer getNumero() {
    return numero;
  }

  public Boolean getOcupado() {
    return ocupado;
  }
}
