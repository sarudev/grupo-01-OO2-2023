package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.Registro;
import com.oo2.grupo01.repositories.IRegistroRepository;
import com.oo2.grupo01.services.IRegistroService;

import lombok.AllArgsConstructor;

@Service("registroService")
@AllArgsConstructor
public class RegistroService implements IRegistroService{
	private IRegistroRepository repository;

	@Override
	public void agregar(Registro registro) {
		repository.save(registro);
		
	}

	@Override
	public void eliminar(Long id) {
		Registro registro = repository.findById(id).orElse(null);
		
		if(registro!=null)
			repository.delete(registro);
		
	}
}
