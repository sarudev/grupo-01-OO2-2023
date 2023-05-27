package com.oo2.grupo01.dto;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class EspacioVerdeDTO extends GenericDTO<EspacioVerde> {
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
