package com.oo2.grupo01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.ParkingDTO;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.repositories.IParkingRepository;

import lombok.AllArgsConstructor;

@Service("parkingService")
@AllArgsConstructor
public class ParkingService {
  private IParkingRepository repository;

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
    return new ParkingDTO(parking, true);
  }

  public List<ParkingDTO> toDtoList(List<Parking> lugares) {
    return lugares.stream().map(l -> new ParkingDTO(l, false)).toList();
  }
}
