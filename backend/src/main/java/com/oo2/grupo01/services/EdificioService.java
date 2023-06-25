package com.oo2.grupo01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.repositories.IEdificioRepository;
import com.oo2.grupo01.repositories.IHistorialRepository;

import lombok.AllArgsConstructor;

@Service("edificoService")
@AllArgsConstructor
public class EdificioService {
  @Autowired
  IEdificioRepository repository;

  @Autowired
  IHistorialRepository historialRepository;

  public void add(String nombre) {
    repository.save(new Edificio(nombre));
  }

  public List<Edificio> getAll() {
    return repository.findAll();
  }

  public Edificio get(Long id) {
    return repository.findById(id).orElse(null);
  }

  public EdificioDTO toDto(Edificio edificio) {
    var dto = new EdificioDTO(edificio, true);
    dto.inicializarVariables(edificio, (h) -> historialRepository.save(h));
    return dto;
  }

  public List<EdificioDTO> toDtoList(List<Edificio> lugares) {
    return lugares.stream().map(l -> new EdificioDTO(l, false)).toList();
  }
}
