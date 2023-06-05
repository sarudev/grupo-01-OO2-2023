package com.oo2.grupo01.services.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EspacioVerdeDTO;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.mapeos.EspacioVerdeMapeo;
import com.oo2.grupo01.repositories.IEspacioVerdeRepository;
import com.oo2.grupo01.services.IEspacioVerdeService;

import lombok.AllArgsConstructor;

@Service("espacioVerdeService")
@AllArgsConstructor
public class EspacioVerdeService implements IEspacioVerdeService {
	private IEspacioVerdeRepository repository;

	@Override
	public void agregar(EspacioVerde object) {
		if (object != null)
			repository.save(object);
	}

	@Override
	public void eliminar(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<EspacioVerdeDTO> traerTodos() {
		return EspacioVerdeMapeo.toDtoList(repository.findAll());
	}

	@Override
	public EspacioVerdeDTO traerConDependencias(Long id) {
		EspacioVerde espacioVerde = repository.traerConDependencias(id).orElse(null);
		return new EspacioVerdeDTO(espacioVerde);
	}

}
