package com.oo2.grupo01.dto;

import java.util.List;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.mapeos.AulaMapeos;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class EdificioDTO extends GenericDTO {
	private String nombre;
	private Boolean luces;
	private List<AulaDTO> aulas;

	public EdificioDTO(Edificio edificio) {
		super(edificio.getIdLugar(), edificio.getLugar(), edificio.getSensores());
		this.nombre = edificio.getNombre();

		this.aulas = AulaMapeos.toDtoList(edificio.getAulas());

		this.luces = null;
		this.registros = null;

	}

	// ToString modificado para que pueda usarse como registro de los sensores
	@Override
	public String toString() {
		return "luces=" + luces;
	}

	@Override
	public void inicializarVariables() {
		for (var sensor : sensores) {
			if (sensor.isActivo()) {

				switch (sensor.getTipo()) {
				case TIEMPO:
					SensorTiempo s = (SensorTiempo) Util.convertirSensor(sensor);
					luces = s.hayLuzSolar();
					break;

				default:
					break;

				}

			}
		}
	}

}
