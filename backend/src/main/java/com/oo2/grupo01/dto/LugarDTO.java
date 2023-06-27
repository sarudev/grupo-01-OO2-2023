package com.oo2.grupo01.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.oo2.grupo01.Utils.SensorUtil;
import com.oo2.grupo01.entities.Historial;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.entities.enums.Lugares;
import com.oo2.grupo01.entities.enums.Sensores;

import lombok.Getter;

@Getter
public abstract class LugarDTO {
	protected Long id;
	protected Lugares tipo;
	protected String nombre;
	protected List<Sensor> sensores = new ArrayList<>();
	protected List<Historial> historial = new ArrayList<>();

	protected LugarDTO(Long id, String nombre, Lugares tipo, List<Sensor> sensores, List<Historial> historial) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.historial = historial;

		for (var sensor : sensores) {
			var sen = SensorUtil.convertirSensor(sensor);

			if (sen != null)
				this.sensores.add(sen);
		}

	}

	public abstract void inicializarVariables();

	protected List<Historial> getBy(Sensores tipo) {
		return historial.stream().filter(h -> h.getTipo().equals(tipo)).toList();
	}
	
	protected boolean hayHistorialReciente(Sensores tipoSensor) {
		// si el tipo coincide y la diferencia de tiempo es menor o igual a 2 minutos, 45 segundos, las
		//variables se van a inicializar con la misma informacion
		return historial.stream().anyMatch(
				t -> ChronoUnit.SECONDS.between(t.getFecha(), LocalDateTime.now()) <= 180 && t.getTipo() == tipoSensor);
	}
	
	protected List<Historial> traerHistorialReciente(Sensores tipo){
		return historial.stream()
				.filter(t -> ChronoUnit.SECONDS.between(t.getFecha(), LocalDateTime.now()) <= 180
				&& t.getTipo() == tipo)
		.toList();
	}
	
}
