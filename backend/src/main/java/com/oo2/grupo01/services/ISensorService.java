package com.oo2.grupo01.services;

import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.Sensores;

public interface ISensorService {
	public Sensor traerSensor(Long id);
	public void switchSensor(Long id);
	public void agregarSensor(Sensores tipo, Lugar lugar);
	public void eliminarSensor(Long id);
}
