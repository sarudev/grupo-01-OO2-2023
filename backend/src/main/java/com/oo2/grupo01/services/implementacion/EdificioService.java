package com.oo2.grupo01.services.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.entities.enums.Lugares;
import com.oo2.grupo01.mapeos.EdificioMapeo;
import com.oo2.grupo01.repositories.IEdificioRepository;
import com.oo2.grupo01.services.IEdificioService;

import lombok.AllArgsConstructor;

@Service("edificoService")
@AllArgsConstructor
public class EdificioService implements IEdificioService {
  private IEdificioRepository repository;

  public void agregar(Lugares lugar, String nombre) {
    if (nombre != null) {
      repository.save(new Edificio(lugar, nombre));
    }
  }

  @Override
  public void eliminar(Long id) {
    repository.deleteById(id);
  }

  @Override
  public Edificio traerConDependencias(Long id) {
    Edificio edificio = repository.traerConDependencias(id).orElse(null);

    return edificio;
  }

  @Override
  public List<EdificioDTO> traerTodos() {
    return EdificioMapeo.toDtoList(repository.findAll());
  }

  @Override
  public Edificio traer(Long id) {
    return repository.findById(id).orElse(null);
  }
}
