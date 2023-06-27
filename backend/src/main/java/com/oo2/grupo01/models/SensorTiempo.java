package com.oo2.grupo01.models;

import java.time.LocalTime;

import com.oo2.grupo01.entities.Sensor;

import lombok.Getter;

@Getter
public class SensorTiempo extends Sensor {

	private LocalTime horaActual;
	private boolean hayLuzSolar;

	private static LocalTime comienzaDia = LocalTime.of(8, 0);
	private static LocalTime comienzaNoche = LocalTime.of(18, 0);

	public SensorTiempo(Sensor sensor) {
		super(sensor);
		horaActual = LocalTime.now();
		hayLuzSolar = hayLuzSolar();
	}

	public boolean hayLuzSolar() {
		return horaActual.isAfter(comienzaDia) && horaActual.isBefore(comienzaNoche);
	}

	@Override
	public String toString() {
		return "hayLuzSolar=" + hayLuzSolar;
	}

}
