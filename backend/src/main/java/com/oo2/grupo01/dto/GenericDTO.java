package com.oo2.grupo01.dto;

import java.util.Set;

import com.oo2.grupo01.entities.Lugares;
import com.oo2.grupo01.entities.Registro;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.models.SensorBascula;
import com.oo2.grupo01.models.SensorCamara;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public abstract class GenericDTO {
	protected Long id;
	protected Lugares type;
	protected Set<Sensor> sensores;
	protected Set<Registro> registros;
	
	protected GenericDTO(Long id, Lugares type, Set<Sensor> sensores) {
		this.id = id;
		this.type = type;
		
		for(var sensor : sensores) {
			switch(sensor.getTipo()) {
			case BASCULA:
				this.sensores.add(new SensorBascula(sensor));
				break;
			case CAMARA:
				this.sensores.add(new SensorCamara(sensor));
				break;
			case HUMEDAD:
				this.sensores.add(new SensorHumedad(sensor));
				break;
			case TIEMPO:
				this.sensores.add(new SensorTiempo(sensor));
				break;	
			}
			
			this.registros.addAll(sensor.getRegistros());
		}
		
	}
	
	public abstract void inicializarVariables();
	
}
