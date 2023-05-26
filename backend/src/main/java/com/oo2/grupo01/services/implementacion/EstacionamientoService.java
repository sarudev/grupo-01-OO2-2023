package com.oo2.grupo01.services.implementacion;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EstacionamientoDTO;
import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.repositories.IEstacionamientoRepository;
import com.oo2.grupo01.services.IEstacionamientoService;

@Service("estacionamientoService")
public class EstacionamientoService implements IEstacionamientoService {
  @Qualifier
  IEstacionamientoRepository repository;

  @Override
  public void agregar(Estacionamiento object) {
    if (object != null)
      repository.save(object);
  }

  @Override
  public EstacionamientoDTO traer(Long id) {
    Estacionamiento est = repository.findById(id).orElse(null);

    return new EstacionamientoDTO(est);
  }

  @Override
  public EstacionamientoDTO traerPorNumero(int numero, Long idParking) {
    Estacionamiento est = repository.traerPorNumero(numero, idParking).orElse(null);

    return new EstacionamientoDTO(est);
  }

  @Override
  public void eliminar(Long id) {
    repository.deleteById(id);
  }
}
