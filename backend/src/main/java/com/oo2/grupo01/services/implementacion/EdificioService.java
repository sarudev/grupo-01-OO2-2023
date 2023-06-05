package com.oo2.grupo01.services.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.mapeos.EdificioMapeo;
import com.oo2.grupo01.repositories.IEdificioRepository;
import com.oo2.grupo01.services.IEdificioService;

import lombok.AllArgsConstructor;

@Service("edificoService")
@AllArgsConstructor
public class EdificioService implements IEdificioService {
	private IEdificioRepository repository;

	@Override
	public void agregar(Edificio object) {
		if (object != null)
			repository.save(object);
	}

	@Override
	public void eliminar(Long id) {
		repository.deleteById(id);
	}

	@Override
	public EdificioDTO traerConDependencias(Long id) {
		Edificio edificio = repository.traerConDependencias(id).orElse(null);
		
		return new EdificioDTO(edificio);
	}

	@Override
	public List<EdificioDTO> traerTodos() {
		return EdificioMapeo.toDtoList(repository.findAll());
	}
}
