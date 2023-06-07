package com.oo2.grupo01.dto;

import java.util.Set;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.models.SensorCamara;

import com.oo2.grupo01.entities.Registro;

import lombok.Getter;

@Getter
public class AulaDTO extends GenericDTO {
	private String nombre;
	private Boolean luces;
	private Boolean persianasAbiertas;
	private Set<Registro> registros;
	
	public AulaDTO(Aula aula) {
		super(aula.getIdLugar(), aula.getLugar(), aula.getSensores());
		this.nombre = aula.getNombre();

		this.luces = null;
		this.persianasAbiertas = null;
		this.registros = null;
		
		for (var s : aula.getSensores() ) {
			if (s.isActivo()) {
				switch (s.getTipo()) {
				case CAMARA:
					SensorCamara sensor = (SensorCamara) Util.convertirSensor(s);
					luces = persianasAbiertas = sensor.cantPersonas() > 0;
					
					break;
				default:
					break;

				}
			}
			
			this.registros.addAll(s.getRegistros());
			
		}
	}

	//ToString modificado para que pueda usarse como registro de los sensores
	@Override
	public String toString() {
		return "luces=" + luces + ", persianasAbiertas=" + persianasAbiertas;
	}
	
	
}
