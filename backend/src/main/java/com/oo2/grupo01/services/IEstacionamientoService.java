package com.oo2.grupo01.services;

import com.oo2.grupo01.dto.EstacionamientoDTO;
import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.entities.Parking;

public interface IEstacionamientoService extends IGenericService<Estacionamiento, EstacionamientoDTO> {
  void add(Parking parking, String nombre);

  public Estacionamiento get(Parking parking, String nombre);
}
