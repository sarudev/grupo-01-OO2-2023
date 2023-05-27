package com.oo2.grupo01.dto;

import java.util.List;

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.mapeos.EstacionamientoMapeos;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class ParkingDTO extends GenericDTO<Parking> {
	private String ubicacion;
	private Boolean luces;
	private List<EstacionamientoDTO> estacionamientos;

	public ParkingDTO(Parking parking) {
		super(parking.getIdLugar(), parking.getLugar(), parking.getSensores());
		this.ubicacion = parking.getUbicacion();
		this.estacionamientos = EstacionamientoMapeos.toDtoList(parking.getEstacionamientos());

		this.luces = null;

		for (var sensor : sensores) {
			if (sensor.isActivo()) {
				switch (sensor.getTipo()) {
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
