package com.oo2.grupo01.dto;

import java.util.List;
import java.util.Set;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.entities.Registro;
import com.oo2.grupo01.mapeos.AulaMapeos;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class EdificioDTO extends GenericDTO<Edificio> {
	private String nombre;
	private Boolean luces;
	private List<AulaDTO> aulas;
	private Set<Registro> registros;

	public EdificioDTO(Edificio edificio) {
		super(edificio.getIdLugar(), edificio.getLugar(), edificio.getSensores());
		this.nombre = edificio.getNombre();

		this.aulas = AulaMapeos.toDtoList(edificio.getAulas());

		this.luces = null;
		this.registros = null;

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
			this.registros.addAll(sensor.getRegistros());
		}
	}
}
