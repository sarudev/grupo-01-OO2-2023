package com.oo2.grupo01.services;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.repositories.ISensorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SensorService {
	private final ISensorRepository sensorRepository;

	public Sensor get(Long id) {
		return sensorRepository.findById(id).orElse(null);
	}

	public void toggle(Sensor sensor) {
		sensor.setActivo(!sensor.isActivo());
		sensorRepository.save(sensor);
	}

	public Sensor add(Lugar lugar, Sensores tipo) {
		return sensorRepository.save(new Sensor(tipo, lugar));
	}
}
