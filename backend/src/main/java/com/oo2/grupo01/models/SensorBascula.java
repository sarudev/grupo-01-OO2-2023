package com.oo2.grupo01.models;

import com.oo2.grupo01.entities.Sensor;

import lombok.ToString;

@ToString
public class SensorBascula extends Sensor {

	public final static double pesoLimite = 300;
	private double pesoActual;
	
	public SensorBascula(Sensor sensor) {
		super(sensor);
		pesoActual = pesoActual();
	}

	public double pesoActual() {
		return (Math.random() % 2) * 500;
	}
	
	public boolean superoLimite() {
		return pesoActual >= pesoLimite;
	}

}
