package com.oo2.grupo01.dto;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.models.SensorBascula;

import lombok.Getter;

@Getter
public class EstacionamientoDTO extends GenericDTO {
	private Boolean ocupado;

	public EstacionamientoDTO(Estacionamiento est) {
		super(est.getIdLugar(), Integer.toString(est.getNumero()) , est.getLugar(), est.getSensores());
		
		this.ocupado = null;
		this.registros = null;

	}

	@Override
	public void inicializarVariables() {
		for (var sensor : sensores) {
			if (sensor.isActivo()) {
				switch (sensor.getTipo()) {
				case BASCULA:
					SensorBascula sensorBascula = (SensorBascula) Util.convertirSensor(sensor);
					ocupado = sensorBascula.superoLimite();
					break;
				default:
					break;

				}
			}
		}
	}

}
