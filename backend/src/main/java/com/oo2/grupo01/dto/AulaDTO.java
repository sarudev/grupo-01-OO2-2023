package com.oo2.grupo01.dto;

import java.util.Arrays;
import java.util.List;

import com.oo2.grupo01.Utils.DescRegs;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.Historial;
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

				} else if (s instanceof SensorTemperatura) {

					if (hayHistorialReciente(s.getTipo())) {
						System.out.println(1);
						List<Historial> historialReciente = traerHistorialReciente(s.getTipo());

						if (!historialReciente.isEmpty()) {
							for (Historial h : historialReciente) {
								String desc = h.getDescripcion();
								if (desc.contains(DescRegs.ESTUFA_ON)) {
									estufas = true;
								} else if (desc.contains(DescRegs.AIRE_ON)) {
									aireAcondicionado = true;
								} else if (desc.contains(DescRegs.AIRE_OFF)) {
									aireAcondicionado = false;
								} else if (desc.contains(DescRegs.ESTUFA_OFF)) {
									estufas = false;
								}
							}
						}

					} else {
						SensorTemperatura sen = new SensorTemperatura(s);

						estufas = sen.getTemperatura() < 13;

						aireAcondicionado = sen.getTemperatura() > 25;
					}

				}
			}
		}
	}

}
