package com.oo2.grupo01.services.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.repositories.IEdificioRepository;
import com.oo2.grupo01.services.IEdificioService;

import lombok.AllArgsConstructor;

@Service("edificoService")
@AllArgsConstructor
public class EdificioService implements IEdificioService {
  private IEdificioRepository repository;

  public void add(String nombre) {
    repository.save(new Edificio(nombre));
  }

  public List<Edificio> getAll() {
    return repository.findAll();
  }

  public Edificio get(String nombre) {
    System.out.println("edificio nombre: " + nombre);
    System.out.println("edificio: " + repository.findByName(nombre));
    return repository.findByName(nombre);
  }

  public EdificioDTO toDto(Edificio edificio) {
    return new EdificioDTO(edificio);
  }

  public List<EdificioDTO> toTdoList(List<Edificio> lugares) {
    return lugares.stream().map(l -> new EdificioDTO(l)).toList();
  }
}
