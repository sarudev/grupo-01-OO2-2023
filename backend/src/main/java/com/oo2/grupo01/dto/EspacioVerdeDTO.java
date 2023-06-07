package com.oo2.grupo01.dto;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class EspacioVerdeDTO extends GenericDTO {
	private String ubicacion;
	private Double humedad;
	private Boolean luces;
	private Boolean aspersoresEncendidos;

	public EspacioVerdeDTO(EspacioVerde espacioVerde) {
		super(espacioVerde.getIdLugar(), espacioVerde.getLugar(), espacioVerde.getSensores());
		this.ubicacion = espacioVerde.getUbicacion();

		this.humedad = null;
		this.luces = null;
		this.aspersoresEncendidos = null;
		this.registros = null;

	}

	// ToString modificado para que pueda usarse como registro de los sensores
	@Override
	public String toString() {
		return "humedad=" + humedad + ", luces=" + luces + ", aspersoresEncendidos=" + aspersoresEncendidos;
	}

	@Override
	public void inicializarVariables() {
		for (var sensor : sensores) {
			if (sensor.isActivo()) {
				switch (sensor.getTipo()) {
				case HUMEDAD:
					SensorHumedad sensorHumedad = (SensorHumedad) Util.convertirSensor(sensor);

					humedad = sensorHumedad.humedad();
					aspersoresEncendidos = humedad < 50;

					break;
				case TIEMPO:
					SensorTiempo sensorTiempo = (SensorTiempo) Util.convertirSensor(sensor);
					luces = sensorTiempo.hayLuzSolar();
					break;
				default:
					break;

				}
			}
		}
	}

}
