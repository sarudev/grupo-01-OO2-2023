package com.oo2.grupo01.dto;

import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.Utils.DescRegs;
import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.models.SensorBascula;

import lombok.Getter;

@Getter
public class EstacionamientoDTO extends LugarDTO {
	private Boolean libre;
	private ParkingDTO lugar;

	public static List<Sensores> allowedSensores = Arrays.asList(new Sensores[] { Sensores.bascula });

	public EstacionamientoDTO(Estacionamiento est, boolean conParking) {
		super(est.getIdLugar(), est.getNombre(), est.getTipo(), est.getSensores(), est.getHistorial());

		this.libre = null;

		if (conParking)
			this.lugar = new ParkingDTO(est.getLugar(), false);
	}

	@Override
	public void inicializarVariables() {
		for (var s : sensores) {
			if (s.isActivo()) {
				if (s instanceof SensorBascula) {

					if (hayHistorialReciente(s.getTipo())) {
						List<Historial> registroReciente = traerHistorialReciente(s.getTipo());
						if (!registroReciente.isEmpty()) {
							String desc = registroReciente.get(0).getDescripcion();
							if (desc.contains(DescRegs.ESTACIONAMIENTO_LIBRE)) {
								libre = true;
							} else {
								libre = false;
							}

						}
					}else {
						SensorBascula sen = new SensorBascula(s);
						this.libre = !sen.isSuperoLimite();
					}



				}
			}
		}
	}
}
