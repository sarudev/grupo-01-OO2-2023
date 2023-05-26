package com.oo2.grupo01.services.implementacion;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.repositories.IEdificioRepository;
import com.oo2.grupo01.services.IEdificioService;

@Service("edificoService")
public class EdificioService implements IEdificioService {
  @Qualifier
  IEdificioRepository repository;

  @Override
  public void agregar(Edificio object) {
    if (object != null)
      repository.save(object);
  }

  @Override
  public EdificioDTO traer(Long id) {
    Edificio est = repository.findById(id).orElse(null);

    return new EdificioDTO(est);
  }

  @Override
  public EdificioDTO traerPorNombre(String nombre) {
    Edificio est = repository.traerPorNombre(nombre).orElse(null);

    return new EdificioDTO(est);
  }

  @Override
  public void eliminar(Long id) {
    repository.deleteById(id);
  }
}
