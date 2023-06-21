package com.oo2.grupo01.models;

import com.oo2.grupo01.entities.Sensor;

import lombok.Getter;

@Getter
public class SensorCamara extends Sensor {
	
	private int cantPersonas;
	
	public SensorCamara(Sensor sensor) {
		super(sensor);
		this.cantPersonas = cantPersonas();
	}
	
	public int cantPersonas() {
		return Integer.parseInt(String.valueOf(Math.random() % 20));
	}

	@Override
	public String toString() {
		return "cantPersonas=" + cantPersonas;
	}

	
	
}
