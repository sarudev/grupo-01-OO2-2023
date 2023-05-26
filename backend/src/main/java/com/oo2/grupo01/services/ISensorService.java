package com.oo2.grupo01.services;

import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.Sensores;

public interface ISensorService extends IGenericService<Sensor, Sensor> {
  public void agregar(Sensores tipo, Lugar lugar);

  public void switchSensor(Long id);
}
