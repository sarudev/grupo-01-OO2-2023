package com.oo2.grupo01.services;

import java.time.LocalDateTime;
import java.util.Set;

import com.oo2.grupo01.entities.Sensor;

public interface IRegistroService {
	public void agregar(Sensor sensor, String informacion, LocalDateTime fechaHora);
	
	public void eliminar(Long id);
	
	public void agregar(Set<Sensor> sensores);
}
