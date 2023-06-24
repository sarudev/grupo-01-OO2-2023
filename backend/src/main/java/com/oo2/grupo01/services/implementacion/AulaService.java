package com.oo2.grupo01.services.implementacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.repositories.IAulaRepository;
import com.oo2.grupo01.services.IAulaService;

import lombok.AllArgsConstructor;

@Service("aulaService")
@AllArgsConstructor
public class AulaService implements IAulaService {
  private IAulaRepository repository;

  public void add(Edificio edificio, String nombre) throws Exception {
    repository.save(new Aula(edificio, nombre));
  }

  public List<Aula> getAll(Long idEdificio) {
    var aulas = repository.findAll();

    List<Aula> res = new ArrayList<Aula>();

    for (var a : aulas) {
      System.out.println(a);
      if (a.getLugar() != null && a.getLugar().getIdLugar() == idEdificio) {
        res.add(a);
      }
    }

    return res;
  }

  public List<Aula> getAll() {
    return repository.findAll();
  }

  public Aula get(Long idEdificio, Long idAula) {
    return repository.get(idEdificio, idAula);
  }

  public AulaDTO toDto(Aula aula) {
    return new AulaDTO(aula);
  }

  public List<AulaDTO> toTdoList(List<Aula> lugares) {
    return lugares.stream().map(l -> new AulaDTO(l)).toList();
  }
}
