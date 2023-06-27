package com.oo2.grupo01.models;

import com.oo2.grupo01.entities.Sensor;

import lombok.Getter;

@Getter
public class SensorTemperatura extends Sensor {
	private double temperatura;
	
	public SensorTemperatura(Sensor sensor) {
		super(sensor);
		temperatura = temperatura();
	}

	public double temperatura() {
		return Math.round(Math.random() % 51);
	}

	@Override
	public String toString() {
		return "temperatura=" + temperatura();
	}
}
