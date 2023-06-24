package com.oo2.grupo01.services.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EspacioVerdeDTO;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.repositories.IEspacioVerdeRepository;
import com.oo2.grupo01.services.IEspacioVerdeService;

import lombok.AllArgsConstructor;

@Service("espacioVerdeService")
@AllArgsConstructor
public class EspacioVerdeService implements IEspacioVerdeService {
  private IEspacioVerdeRepository repository;

  public void add(String nombre) {
    repository.save(new EspacioVerde(nombre));
  }

  public List<EspacioVerde> getAll() {
    return repository.findAll();
  }

  public EspacioVerde get(String nombre) {
    return repository.findByName(nombre);
  }

  public EspacioVerdeDTO toDto(EspacioVerde espacioVerde) {
    return new EspacioVerdeDTO(espacioVerde);
  }

  public List<EspacioVerdeDTO> toTdoList(List<EspacioVerde> lugares) {
    return lugares.stream().map(l -> new EspacioVerdeDTO(l)).toList();
  }

}
