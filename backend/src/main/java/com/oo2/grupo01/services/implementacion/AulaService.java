package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.repositories.IAulaRepository;
import com.oo2.grupo01.services.IAulaService;

import lombok.AllArgsConstructor;

@Service("aulaService")
@AllArgsConstructor
public class AulaService implements IAulaService {
	private IAulaRepository repository;

	@Override
	public void agregar(Aula object) {
		if (object != null)
			repository.save(object);
	}

	@Override
	public void eliminar(Long id) {
		repository.deleteById(id);
	}

	@Override
	public AulaDTO traerConDependencias(Long id) {
		Aula aula = repository.traerConDependencias(id).orElse(null);
		
		return new AulaDTO(aula);
	}
}
