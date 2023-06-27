package com.oo2.grupo01.dto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.models.SensorTemperatura;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class AulaDTO extends LugarDTO {
	private String nombre;
	private Boolean luces = null;
	private Boolean persianas = null;
	private Boolean aireAcondicionado = null;
	private Boolean estufas = null;
	private EdificioDTO lugar;

	public static List<Sensores> allowedSensores = Arrays
			.asList(new Sensores[] { Sensores.tiempo, Sensores.temperatura });

	public AulaDTO(Aula aula, boolean conLugar) {
		super(aula.getIdLugar(), aula.getNombre(), aula.getTipo(), aula.getSensores(), aula.getHistorial());
		this.nombre = aula.getNombre();

		if (conLugar)
			this.lugar = new EdificioDTO(aula.getLugar(), false);
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
				} else if (s instanceof SensorTemperatura) {
					SensorTemperatura sen = new SensorTemperatura(s);

					estufas = sen.getTemperatura() < 13;

					aireAcondicionado = sen.getTemperatura() > 25;

				}
			}
		}
	}

}
