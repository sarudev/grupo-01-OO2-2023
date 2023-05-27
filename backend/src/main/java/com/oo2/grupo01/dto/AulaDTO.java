package com.oo2.grupo01.dto;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.models.SensorCamara;

import lombok.Getter;

@Getter
public class AulaDTO extends GenericDTO<Aula> {
	private String nombre;
	private Boolean luces;

	public AulaDTO(Aula aula) {
		super(aula.getIdLugar(), aula.getLugar(), aula.getSensores());
		this.nombre = aula.getNombre();

		this.luces = null;

		for (var s : sensores) {
			if (s.isActivo()) {
				switch (s.getTipo()) {
				case CAMARA:
					SensorCamara sensor = (SensorCamara) Util.convertirSensor(s);
					luces = sensor.cantPersonas() > 0;
					break;
				default:
					break;

				}
			}
		}
	}
}
