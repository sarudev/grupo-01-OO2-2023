package com.oo2.grupo01.services;

import com.oo2.grupo01.entities.Sensor;

public interface IRegistroService {
	public void agregar(Sensor sensor, String informacion);
	
	public void eliminar(Long id);
}
