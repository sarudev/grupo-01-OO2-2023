package com.oo2.grupo01.mapeos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.oo2.grupo01.dto.EstacionamientoDTO;
import com.oo2.grupo01.entities.Estacionamiento;

public class EstacionamientoMapeos {
	//set<Estacionamiento> ------> List<EstacionamientoDto>
	public static List<EstacionamientoDTO> toDtoList(Set<Estacionamiento> setEstacionamiento){
		List<EstacionamientoDTO> estacionamientos = new ArrayList<EstacionamientoDTO>();
		
		for(Estacionamiento e : setEstacionamiento) {
			estacionamientos.add(new EstacionamientoDTO(e));
		}
		
		return estacionamientos;
	}
	
}
