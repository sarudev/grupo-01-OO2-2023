package com.oo2.grupo01.services.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.ParkingDTO;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.repositories.IParkingRepository;
import com.oo2.grupo01.services.IParkingService;

import lombok.AllArgsConstructor;

@Service("parkingService")
@AllArgsConstructor
public class ParkingService implements IParkingService {
  private IParkingRepository repository;

  public void add(String nombre) {
    repository.save(new Parking(nombre));
  }

  public List<Parking> getAll() {
    return repository.findAll();
  }

  public Parking get(String nombre) {
    return repository.findByName(nombre);
  }

  public ParkingDTO toDto(Parking parking) {
    return new ParkingDTO(parking);
  }

  public List<ParkingDTO> toTdoList(List<Parking> lugares) {
    return lugares.stream().map(l -> new ParkingDTO(l)).toList();
  }
}
