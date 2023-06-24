package com.oo2.grupo01.dto;

import java.util.List;

import com.oo2.grupo01.entities.Parking;

import lombok.Getter;

@Getter
public class ParkingDTO extends LugarDTO {
  private Boolean luces;
  private List<EstacionamientoDTO> estacionamientos;

  public ParkingDTO(Parking parking, boolean conEstacionamiento) {
    super(parking.getIdLugar(), parking.getNombre(), parking.getTipo(), parking.getSensores(), parking.getHistorial());

    if (conEstacionamiento)
      this.estacionamientos = parking.getEstacionamientos().stream().map(est -> new EstacionamientoDTO(est, false))
          .toList();

    this.luces = null;
  }

  @Override
  public void inicializarVariables() {

  }
}
