package com.oo2.grupo01.dto;

import java.util.Set;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.entities.Registro;
import com.oo2.grupo01.models.SensorBascula;

import lombok.Getter;

@Getter
public class EstacionamientoDTO extends GenericDTO<Estacionamiento> {
	private Integer numero;
	private Boolean ocupado;
	private Set<Registro> registros;

	public EstacionamientoDTO(Estacionamiento est) {
		super(est.getIdLugar(), est.getLugar(), est.getSensores());
		this.numero = est.getNumero();

		this.ocupado = null;
		this.registros = null;

		for (var sensor : est.getSensores() ) {
			if (sensor.isActivo()) {
				switch(sensor.getTipo()) {
				case BASCULA:
					SensorBascula sensorBascula = (SensorBascula) Util.convertirSensor(sensor);
					ocupado = sensorBascula.superoLimite();
					break;
				default:
					break;
				
				}
			}
			
			this.registros.addAll(sensor.getRegistros());
			
		}
	}
}
