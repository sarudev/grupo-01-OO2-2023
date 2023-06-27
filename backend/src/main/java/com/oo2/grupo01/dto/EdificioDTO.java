package com.oo2.grupo01.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.entities.enums.Sensores;
import com.oo2.grupo01.models.SensorTiempo;

import lombok.Getter;

@Getter
public class EdificioDTO extends LugarDTO {
	private Boolean luces = null;
	private List<AulaDTO> aulas = new ArrayList<>();

	public static List<Sensores> allowedSensores = Arrays.asList(new Sensores[] { Sensores.tiempo });

	public EdificioDTO(Edificio edif, boolean conAulas) {
		super(edif.getIdLugar(), edif.getNombre(), edif.getTipo(), edif.getSensores(), edif.getHistorial());
		if (conAulas)
			this.aulas = edif.getAulas().stream().map(au -> new AulaDTO(au, false)).toList();
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
				}
			}
		}
	}
}
