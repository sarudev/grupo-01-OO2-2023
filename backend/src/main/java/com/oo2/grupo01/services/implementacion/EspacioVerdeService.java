package com.oo2.grupo01.services.implementacion;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EspacioVerdeDTO;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.repositories.IEspacioVerdeRepository;
import com.oo2.grupo01.services.IEspacioVerdeService;

@Service("espacioVerdeService")
public class EspacioVerdeService implements IEspacioVerdeService {
  @Qualifier
  IEspacioVerdeRepository repository;

  @Override
  public void agregar(EspacioVerde object) {
    if (object != null)
      repository.save(object);
  }

  @Override
  public EspacioVerdeDTO traer(Long id) {
    EspacioVerde est = repository.findById(id).orElse(null);

    return new EspacioVerdeDTO(est);
  }

  @Override
  public EspacioVerdeDTO traerPorUbicacion(String ubicacion) {
    EspacioVerde est = repository.traerPorUbicacion(ubicacion).orElse(null);

    return new EspacioVerdeDTO(est);
  }

  @Override
  public void eliminar(Long id) {
    repository.deleteById(id);
  }
}
