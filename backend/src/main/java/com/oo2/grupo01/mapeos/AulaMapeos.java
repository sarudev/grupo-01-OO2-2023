package com.oo2.grupo01.mapeos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.entities.Aula;

public class AulaMapeos {
	//set<Aula> ----> List<AulaDto>
	public static List<AulaDTO> toDtoList(Set<Aula> setAulas){
		List<AulaDTO> aulas = new ArrayList<AulaDTO>();
		
		for(Aula a : setAulas) {
			aulas.add(new AulaDTO(a));
		}
		
		return aulas;
	}
}
