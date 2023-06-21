package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.entities.enums.Lugares;
import com.oo2.grupo01.repositories.IEstacionamientoRepository;
import com.oo2.grupo01.services.IEstacionamientoService;

import lombok.AllArgsConstructor;

@Service("estacionamientoService")
@AllArgsConstructor
public class EstacionamientoService implements IEstacionamientoService {
  private IEstacionamientoRepository repository;

  public void agregar(Lugares tipo, Parking lugar, String numero) {
    if (numero != null)
      repository.save(new Estacionamiento(tipo, lugar, numero));
  }

  @Override
  public void eliminar(Long id) {
    repository.deleteById(id);
  }

  @Override
  public Estacionamiento traerConDependencias(Long id) {
    Estacionamiento estacionamiento = repository.traerConDependencias(id).orElse(null);
    return estacionamiento;
  }

  @Override
  public Estacionamiento traer(Long id) {
    return repository.findById(id).orElse(null);
  }
}
