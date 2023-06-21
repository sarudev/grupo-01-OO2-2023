package com.oo2.grupo01.mapeos;

import java.util.ArrayList;
import java.util.List;

import com.oo2.grupo01.dto.EspacioVerdeDTO;
import com.oo2.grupo01.entities.EspacioVerde;

public class EspacioVerdeMapeo {
	// List<EspacioVerde> ----> List<EspacioVerdeDto>
	public static List<EspacioVerdeDTO> toDtoList(List<EspacioVerde> listEntity) {
		List<EspacioVerdeDTO> espacios = new ArrayList<EspacioVerdeDTO>();

		for (EspacioVerde e : listEntity) {
			espacios.add(new EspacioVerdeDTO(e));
		}

		return espacios;
	}
}
