package com.oo2.grupo01.services;

import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.enums.Sensores;

public interface ISensorService {
  public void add(Sensores tipo, Lugar lugar);

  public void toggleSensor(Long id);
}
