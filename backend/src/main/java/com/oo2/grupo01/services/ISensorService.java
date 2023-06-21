package com.oo2.grupo01.services;

import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.Sensores;

public interface ISensorService {
  public void agregar(Sensores tipo, Lugar lugar);

  public Sensor traer(Long id);
  
  public void eliminar(Long id);
  
  public void switchSensor(Long id);
}
