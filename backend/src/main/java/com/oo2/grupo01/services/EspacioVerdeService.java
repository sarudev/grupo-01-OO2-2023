package com.oo2.grupo01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EspacioVerdeDTO;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.repositories.IEspacioVerdeRepository;

import lombok.RequiredArgsConstructor;

@Service("espacioVerdeService")
@RequiredArgsConstructor
public class EspacioVerdeService {
	private final IEspacioVerdeRepository repository;

	public void add(String nombre) {
		repository.save(new EspacioVerde(nombre));
	}

	public List<EspacioVerde> getAll() {
		return repository.findAll();
	}

	public EspacioVerde get(Long id) {
		return repository.findById(id).orElse(null);
	}

	public EspacioVerdeDTO toDto(EspacioVerde espacioVerde) {
		var dto = new EspacioVerdeDTO(espacioVerde);
		dto.inicializarVariables();
		return dto;
	}

	public List<EspacioVerdeDTO> toDtoList(List<EspacioVerde> lugares) {
		return lugares.stream().map(l -> new EspacioVerdeDTO(l)).toList();
	}

}
