package com.oo2.grupo01.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sensor")
public class Sensor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idSensor;
}
