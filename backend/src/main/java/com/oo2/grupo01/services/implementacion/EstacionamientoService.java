package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EstacionamientoDTO;
import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.repositories.IEstacionamientoRepository;
import com.oo2.grupo01.services.IEstacionamientoService;

import lombok.AllArgsConstructor;

@Service("estacionamientoService")
@AllArgsConstructor
public class EstacionamientoService implements IEstacionamientoService {
	private IEstacionamientoRepository repository;

	@Override
	public void agregar(Estacionamiento object) {
		if (object != null)
			repository.save(object);
	}

	@Override
	public void eliminar(Long id) {
		repository.deleteById(id);
	}

	@Override
	public EstacionamientoDTO traerConDependencias(Long id) {
		Estacionamiento estacionamiento = repository.traerConDependencias(id).orElse(null);
		return new EstacionamientoDTO(estacionamiento);
	}
}
