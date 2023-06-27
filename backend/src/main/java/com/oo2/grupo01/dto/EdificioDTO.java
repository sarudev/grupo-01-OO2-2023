package com.oo2.grupo01.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.Utils.DescRegs;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.entities.Historial;
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
