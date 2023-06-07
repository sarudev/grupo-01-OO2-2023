package com.oo2.grupo01.dto;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.models.SensorCamara;

import lombok.Getter;

@Getter
public class AulaDTO extends GenericDTO {
	private String nombre;
	private Boolean luces;
	private Boolean persianasAbiertas;

	public AulaDTO(Aula aula) {
		super(aula.getIdLugar(), aula.getLugar(), aula.getSensores());
		this.nombre = aula.getNombre();

		this.luces = null;
		this.persianasAbiertas = null;
	}

	// ToString modificado para que pueda usarse como registro de los sensores
	@Override
	public String toString() {
		return "luces=" + luces + ", persianasAbiertas=" + persianasAbiertas;
	}

	@Override
	public void inicializarVariables() {
		for (var s : sensores) {
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

		}

	}

}
