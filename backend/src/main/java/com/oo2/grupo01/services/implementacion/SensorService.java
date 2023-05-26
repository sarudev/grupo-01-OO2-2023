package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.Sensores;
import com.oo2.grupo01.repositories.ISensorRepository;
import com.oo2.grupo01.services.ISensorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SensorService implements ISensorService {

	private ISensorRepository repository;

	@Override
	public Sensor traerSensor(Long id) {
		Sensor sensor = repository.findById(id).orElse(null);

		if (sensor != null) {
			Util.convertirSensor(sensor);
		}

		return sensor;
	}

	@Override
	public void switchSensor(Long id) {
		Sensor sensor = repository.findById(id).orElse(null);

		if (sensor != null) {
			sensor.setActivo(!sensor.isActivo());
			
			repository.save(sensor);
		}

	}

	@Override
	public void agregarSensor(Sensores tipo, Lugar lugar) {
		if(tipo != null && lugar != null) {
			repository.save(new Sensor(tipo, lugar));
		}

	}

	@Override
	public void eliminarSensor(Long id) {
		repository.deleteById(id);
	}

}
