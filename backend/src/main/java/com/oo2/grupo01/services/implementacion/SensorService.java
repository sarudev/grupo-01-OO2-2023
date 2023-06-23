package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.repositories.ISensorRepository;
import com.oo2.grupo01.services.ISensorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service("sensorService")
public class SensorService implements ISensorService {
  private ISensorRepository repository;

  @Override
  public void add(Sensores tipo, Lugar lugar) {

  }

  @Override
  public void toggleSensor(Long id) {

  }
}
