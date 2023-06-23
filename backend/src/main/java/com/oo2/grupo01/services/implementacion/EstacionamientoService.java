package com.oo2.grupo01.services.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EstacionamientoDTO;
import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.repositories.IEstacionamientoRepository;
import com.oo2.grupo01.services.IEstacionamientoService;

import lombok.AllArgsConstructor;

@Service("estacionamientoService")
@AllArgsConstructor
public class EstacionamientoService implements IEstacionamientoService {
  private IEstacionamientoRepository repository;

  @Override
  public void add(Parking parking, String nombre) {
    repository.save(new Estacionamiento(parking, nombre));
  }

  @Override
  public List<Estacionamiento> getAll() {
    return repository.findAll();
  }

  @Override
  public Estacionamiento get(Parking parking, String nombre) {
    return repository.findByName(nombre, parking.getNombre());
  }

  public EstacionamientoDTO toDto(Estacionamiento estacionamiento) {
    return new EstacionamientoDTO(estacionamiento);
  }

  public List<EstacionamientoDTO> toTdoList(List<Estacionamiento> lugares) {
    return lugares.stream().map(l -> new EstacionamientoDTO(l)).toList();
  }
}
