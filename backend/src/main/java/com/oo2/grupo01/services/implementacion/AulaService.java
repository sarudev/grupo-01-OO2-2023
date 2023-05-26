package com.oo2.grupo01.services.implementacion;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.repositories.IAulaRepository;
import com.oo2.grupo01.services.IAulaService;

@Service("aulaService")
public class AulaService implements IAulaService {
  @Qualifier
  IAulaRepository repository;

  @Override
  public void agregar(Aula object) {
    if (object != null)
      repository.save(object);
  }

  @Override
  public AulaDTO traer(Long id) {
    Aula aula = repository.findById(id).orElse(null);

    return new AulaDTO(aula);
  }

  @Override
  public AulaDTO traerPorNombre(String nombre, Long idEdificio) {
    Aula aula = repository.traerPorNombre(nombre, idEdificio).orElse(null);

    return new AulaDTO(aula);
  }

  @Override
  public void eliminar(Long id) {
    repository.deleteById(id);
  }
}
