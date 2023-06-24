package com.oo2.grupo01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.repositories.IAulaRepository;

import lombok.AllArgsConstructor;

@Service("aulaService")
@AllArgsConstructor
public class AulaService {
  private IAulaRepository repository;

  public void add(Edificio edificio, String nombre) throws Exception {
    repository.save(new Aula(edificio, nombre));
  }

  public List<Aula> getAll() {
    return repository.findAll();
  }

  public List<Aula> getAllById(Long idEdificio) {
    return repository.getAllById(idEdificio);
  }

  public Aula get(Long idEdificio, Long idAula) {
    return repository.get(idEdificio, idAula);
  }

  public AulaDTO toDto(Aula aula) {
    return new AulaDTO(aula, true);
  }

  public List<AulaDTO> toDtoList(List<Aula> aula) {
    return aula.stream().map(a -> new AulaDTO(a, false)).toList();
  }
}
