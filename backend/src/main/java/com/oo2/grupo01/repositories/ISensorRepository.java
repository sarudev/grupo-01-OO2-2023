package com.oo2.grupo01.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Sensor;

@Repository("sensorRepository")
public interface ISensorRepository extends JpaRepository<Sensor, Serializable>{
	public Sensor switchSensor(int idSensor, boolean estado);
}
