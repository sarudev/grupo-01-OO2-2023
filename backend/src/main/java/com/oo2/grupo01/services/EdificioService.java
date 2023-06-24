package com.oo2.grupo01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.repositories.IEdificioRepository;

import lombok.AllArgsConstructor;

@Service("edificoService")
@AllArgsConstructor
public class EdificioService {
	
	
  private IEdificioRepository repository;

  public void add(String nombre) {
    repository.save(new Edificio(nombre));
  }

  public List<Edificio> getAll() {
    return repository.findAll();
  }

  public Edificio get(Long id) {
    return repository.findById(id).orElse(null);
  }

  public EdificioDTO toDto(Edificio parking) {
    return new EdificioDTO(parking, true);
  }

  public List<EdificioDTO> toDtoList(List<Edificio> lugares) {
    return lugares.stream().map(l -> new EdificioDTO(l, false)).toList();
  }
}
