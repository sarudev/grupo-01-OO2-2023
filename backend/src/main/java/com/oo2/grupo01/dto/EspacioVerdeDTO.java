package com.oo2.grupo01.dto;

import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.Utils.DescRegs;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.models.SensorHumedad;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class EspacioVerdeDTO extends LugarDTO {
	private Boolean luces;
	private Boolean aspersores;

	public static List<Sensores> allowedSensores = Arrays.asList(new Sensores[] { Sensores.tiempo, Sensores.humedad });

	public EspacioVerdeDTO(EspacioVerde esp) {
		super(esp.getIdLugar(), esp.getNombre(), esp.getTipo(), esp.getSensores(), esp.getHistorial());

		this.luces = null;
		this.aspersores = null;
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

				} else if (s instanceof SensorHumedad) {

					if (hayHistorialReciente(s.getTipo())) {
						List<Historial> historialReciente = traerHistorialReciente(s.getTipo());

						if (!historialReciente.isEmpty()) {
							String desc = historialReciente.get(0).getDescripcion();

							if (desc.contains(DescRegs.ASPERSORES_ON)) {
								aspersores = true;
							} else {
								aspersores = false;
							}

						}
					} else {
						SensorHumedad sen = new SensorHumedad(s);
						this.aspersores = sen.humedadBaja();
					}

				}
			}
		}
	}

}
