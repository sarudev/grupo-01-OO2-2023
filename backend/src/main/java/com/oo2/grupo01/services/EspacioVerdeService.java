package com.oo2.grupo01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EspacioVerdeDTO;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.repositories.IEspacioVerdeRepository;
import com.oo2.grupo01.repositories.IHistorialRepository;

import lombok.AllArgsConstructor;

@Service("espacioVerdeService")
@AllArgsConstructor
public class EspacioVerdeService {
  @Autowired
  IEspacioVerdeRepository repository;

  @Autowired
  IHistorialRepository historialRepository;

  public void add(String nombre) {
    repository.save(new EspacioVerde(nombre));
  }

  public List<EspacioVerde> getAll() {
    return repository.findAll();
  }

  public EspacioVerde get(Long id) {
    return repository.findById(id).orElse(null);
  }

  public EspacioVerdeDTO toDto(EspacioVerde espacioVerde) {
    var dto = new EspacioVerdeDTO(espacioVerde);
    dto.inicializarVariables(espacioVerde, (h) -> historialRepository.save(h));
    return dto;
  }

  public List<EspacioVerdeDTO> toDtoList(List<EspacioVerde> lugares) {
    return lugares.stream().map(l -> new EspacioVerdeDTO(l)).toList();
  }

}
