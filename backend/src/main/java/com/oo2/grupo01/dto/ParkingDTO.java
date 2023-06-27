package com.oo2.grupo01.dto;

import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.Utils.DescRegs;
import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class ParkingDTO extends LugarDTO {
	private Boolean luces;
	private List<EstacionamientoDTO> estacionamientos;

	public static List<Sensores> allowedSensores = Arrays.asList(new Sensores[] { Sensores.tiempo });

	public ParkingDTO(Parking parking, boolean conEstacionamiento) {
		super(parking.getIdLugar(), parking.getNombre(), parking.getTipo(), parking.getSensores(),
				parking.getHistorial());

		if (conEstacionamiento)
			this.estacionamientos = parking.getEstacionamientos().stream()
					.map(est -> new EstacionamientoDTO(est, false)).toList();

		this.luces = null;
	}

	@Override
	public void inicializarVariables() {
		for (var s : sensores) {
			if (s.isActivo()) {
				if (s instanceof SensorTiempo) {
					if (hayHistorialReciente(s.getTipo())) {
						List<Historial> registroReciente = traerHistorialReciente(s.getTipo());

						if (!registroReciente.isEmpty()) {
							String desc = registroReciente.get(0).getDescripcion();

							if (desc.contains(DescRegs.LUCES_ON)) {
								luces = true;
							} else {
								luces = false;
							}

						}

					} else {
						SensorTiempo sen = new SensorTiempo(s);

						// si ahora es despues de las 8 y antes de las 6
						if (sen.hayLuzSolar()) {
							this.luces = false;

						} else {
							this.luces = true;
						}
					}
				}
			}
		}
	}
}
