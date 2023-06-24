package com.oo2.grupo01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.EstacionamientoDTO;
import com.oo2.grupo01.entities.Estacionamiento;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.repositories.IEstacionamientoRepository;

import lombok.AllArgsConstructor;

@Service("estacionamientoService")
@AllArgsConstructor
public class EstacionamientoService {
  private IEstacionamientoRepository repository;

  public void add(Parking parking, String nombre) {
    repository.save(new Estacionamiento(parking, nombre));
  }

  public List<Estacionamiento> getAll() {
    return repository.findAll();
  }

  public List<Estacionamiento> getAllById(Long idParking) {
    return repository.getAllById(idParking);
  }

  public Estacionamiento get(Long idParking, Long idEstacionamiento) {
    return repository.get(idParking, idEstacionamiento);
  }

  public EstacionamientoDTO toDto(Estacionamiento estacionamiento) {
    return new EstacionamientoDTO(estacionamiento, true);
  }

  public List<EstacionamientoDTO> toDtoList(List<Estacionamiento> lugares) {
    return lugares.stream().map(l -> new EstacionamientoDTO(l, false)).toList();
  }
}
