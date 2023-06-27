package com.oo2.grupo01.dto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.entities.EspacioVerde;
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

					LocalDateTime now = LocalDateTime.now();
					LocalDateTime diaToday = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
							8, 0);
					LocalDateTime nocheToday = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
							18, 0);

					// si ahora es despues de las 8 y antes de las 6
					if (now.isAfter(diaToday) && now.isBefore(nocheToday)) {
						this.luces = false;

					} else {
						this.luces = true;
					}

				} else if (s instanceof SensorHumedad) {
					SensorHumedad sen = (SensorHumedad) s;
					this.aspersores = sen.humedadBaja();

				}
			}
		}
	}

}
