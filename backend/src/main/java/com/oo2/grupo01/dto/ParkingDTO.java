package com.oo2.grupo01.dto;

import java.util.Set;

import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.entities.Sensores;
import com.oo2.grupo01.models.SensorTiempo;

public class ParkingDTO extends GenericDTO<Parking> {
  String ubicacion;
  Boolean luces;
  Set<Estacionamiento> estacionamientos;

  public ParkingDTO(Parking parking) {
    super(parking.getIdLugar(), parking.getLugar(), parking.getSensores());
    this.ubicacion = parking.getUbicacion();
    this.estacionamientos = parking.getEstacionamientos();

    for (var sensor : sensores) {
      if (sensor.getTipo() == Sensores.TIEMPO && ((SensorTiempo) sensor).isActivo()) {
        this.luces = !((SensorTiempo) sensor).hayLuzSolar();
      }
    }
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public Boolean getLuces() {
    return luces;
  }

  public Set<Estacionamiento> getEstacionamientos() {
    return estacionamientos;
  }
}
