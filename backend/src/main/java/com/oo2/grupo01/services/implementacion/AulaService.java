package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.entities.Lugares;
import com.oo2.grupo01.repositories.IAulaRepository;
import com.oo2.grupo01.services.IAulaService;

import lombok.AllArgsConstructor;

@Service("aulaService")
@AllArgsConstructor
public class AulaService implements IAulaService {
	private IAulaRepository repository;

	public void agregar(Lugares lugar, Edificio edificio, String nombre) {
		if(nombre!=null) {
			repository.save(new Aula(lugar,edificio,nombre));
		}
	}

	@Override
	public void eliminar(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Aula traerConDependencias(Long id) {
		Aula aula = repository.traerConDependencias(id).orElse(null);
		
		return aula;
	}

	@Override
	public Aula traer(Long id) {
		return repository.findById(id).orElse(null);
	}
}







