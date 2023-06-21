package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.repositories.ISensorRepository;
import com.oo2.grupo01.services.ISensorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service("sensorService")
public class SensorService implements ISensorService {
  private ISensorRepository repository;

  @Override
  public void switchSensor(Long id) {
    Sensor sensor = repository.findById(id).orElse(null);

    if (sensor != null) {
      sensor.setActivo(!sensor.isActivo());

      repository.save(sensor);
    }

  }

  @Override
  public void agregar(Sensores tipo, Lugar lugar) {
    if (tipo != null && lugar != null) {
      repository.save(new Sensor(tipo, lugar));
    }
  }

  @Override
  public Sensor traer(Long id) {
    Sensor sensor = repository.findById(id).orElse(null);

    if (sensor != null) {
      sensor = Util.convertirSensor(sensor);
    }

    return sensor;
  }

  @Override
  public void eliminar(Long id) {
    repository.deleteById(id);
  }

}
