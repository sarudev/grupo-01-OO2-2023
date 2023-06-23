package com.oo2.grupo01.services.implementacion;

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

  @Override
  public void add(Edificio edificio, String nombre) {
    repository.save(new Aula(edificio, nombre));
  }

  @Override
  public List<Aula> getAll() {
    return repository.findAll();
  }

  @Override
  public Aula get(String edificio, String nombre) throws Exception {
    if (edificio == null)
      throw new Exception("Edificio no encontrado");
    System.out.println("aula nombre: " + nombre);
    System.out.println("edificio nombre: " + edificio);
    return repository.findByName(nombre, edificio);
  }

  public AulaDTO toDto(Aula aula) {
    return new AulaDTO(aula);
  }

  public List<AulaDTO> toTdoList(List<Aula> lugares) {
    return lugares.stream().map(l -> new AulaDTO(l)).toList();
  }
}
