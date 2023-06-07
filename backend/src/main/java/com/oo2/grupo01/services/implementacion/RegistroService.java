package com.oo2.grupo01.services.implementacion;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.Registro;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.repositories.IRegistroRepository;
import com.oo2.grupo01.services.IRegistroService;

import lombok.AllArgsConstructor;

@Service("registroService")
@AllArgsConstructor
public class RegistroService implements IRegistroService{
	private IRegistroRepository repository;
	
	@Override
	public void agregar(Sensor sensor, String informacion, LocalDateTime fechaHora) {
		if(informacion!=null)
			repository.save(new Registro(sensor, informacion, fechaHora));
	}

	@Override
	public void eliminar(Long id) {
		Registro registro = repository.findById(id).orElse(null);
		
		if(registro!=null)
			repository.delete(registro);
		
	}

	@Override
	public void agregar(Set<Sensor> sensores) {
		for(Sensor s : sensores) {
			agregar(s, s.toString(), LocalDateTime.now());
		}
	}
}
