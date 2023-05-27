package com.oo2.grupo01.models;

import com.oo2.grupo01.entities.Sensor;

import lombok.ToString;

@ToString
public class SensorHumedad extends Sensor {
	
	public SensorHumedad(Sensor sensor) {
		super(sensor);
	}
	
	public double humedad() {
		return Math.random() % 75+ 25;
	}
}
