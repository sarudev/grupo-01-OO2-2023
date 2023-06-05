package com.oo2.grupo01.mapeos;

import java.util.ArrayList;
import java.util.List;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;

public class EdificioMapeo {
	// List<Edificio> ----> List<EdificioDTO>
	public static List<EdificioDTO> toDtoList(List<Edificio> listEntity) {
		List<EdificioDTO> edificios = new ArrayList<EdificioDTO>();

		for (Edificio e : listEntity) {
			edificios.add(new EdificioDTO(e));
		}

		return edificios;
	}
}
