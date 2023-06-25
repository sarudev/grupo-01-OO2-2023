package com.oo2.grupo01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.ParkingDTO;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.repositories.IHistorialRepository;
import com.oo2.grupo01.repositories.IParkingRepository;

import lombok.AllArgsConstructor;

@Service("parkingService")
@AllArgsConstructor
public class ParkingService {
  @Autowired
  IParkingRepository repository;

  @Autowired
  IHistorialRepository historialRepository;

  public void add(String nombre) {
    repository.save(new Parking(nombre));
  }

  public List<Parking> getAll() {
    return repository.findAll();
  }

  public Parking get(Long id) {
    return repository.findById(id).orElse(null);
  }

  public ParkingDTO toDto(Parking parking) {
    var dto = new ParkingDTO(parking, true);
    dto.inicializarVariables(parking, (h) -> historialRepository.save(h));
    return dto;
  }

  public List<ParkingDTO> toDtoList(List<Parking> lugares) {
    return lugares.stream().map(l -> new ParkingDTO(l, false)).toList();
  }
}
