package com.oo2.grupo01.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Sensor;

@Repository("sensorRepository")
public interface ISensorRepository extends JpaRepository<Sensor, Long>{

	public List<Sensor> findAll();
	
	
}
