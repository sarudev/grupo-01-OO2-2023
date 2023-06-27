package com.oo2.grupo01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.repositories.IEdificioRepository;

import lombok.RequiredArgsConstructor;

@Service("edificoService")
@RequiredArgsConstructor
public class EdificioService {
	private final IEdificioRepository repository;

	public void add(String nombre) {
		repository.save(new Edificio(nombre));
	}

	public List<Edificio> getAll() {
		return repository.findAll();
	}

	public Edificio get(Long id) {
		return repository.findById(id).orElse(null);
	}

	public EdificioDTO toDto(Edificio edificio) {
		var dto = new EdificioDTO(edificio, true);
		dto.inicializarVariables();
		return dto;
	}

	public List<EdificioDTO> toDtoList(List<Edificio> lugares) {
		return lugares.stream().map(l -> new EdificioDTO(l, false)).toList();
	}
}
