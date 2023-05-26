package com.oo2.grupo01.dto;

import java.util.List;

import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.entities.Sensores;
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
			if (sensor.getTipo() == Sensores.TIEMPO && ((SensorTiempo) sensor).isActivo()) {
				this.luces = !((SensorTiempo) sensor).hayLuzSolar();
			}
		}
	}
}
