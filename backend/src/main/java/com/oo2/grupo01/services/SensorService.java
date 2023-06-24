package com.oo2.grupo01.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.repositories.ISensorRepository;

import lombok.AllArgsConstructor;

@Service("sensorService")
@AllArgsConstructor
public class SensorService {

	@Autowired
	private ISensorRepository sensorRepository;
	
	public List<Sensor> findAllSensores(){
		return sensorRepository.findAll();
	}
	
	public Optional<Sensor> findSensorById(long id){
		return sensorRepository.findById(id);
	}
	
	
	
	
}
