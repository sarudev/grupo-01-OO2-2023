package com.oo2.grupo01.services.implementacion;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.ParkingDTO;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.repositories.IParkingRepository;
import com.oo2.grupo01.services.IParkingService;

@Service("parkingService")
public class ParkingService implements IParkingService {
  @Qualifier
  private IParkingRepository repository;

  @Override
  public void agregar(Parking parking) {
    if (parking != null) {
      repository.save(parking);
    }
  }

  @Override
  public ParkingDTO traer(Long id) {
    Parking parking = repository.findById(id).orElse(null);

    return new ParkingDTO(parking);
  }

  @Override
  public ParkingDTO traerPorUbicacion(String ubicacion) {
    Parking parking = repository.traerPorUbicacion(ubicacion).orElse(null);

    return new ParkingDTO(parking);
  }

  @Override
  public void eliminar(Long id) {
    repository.deleteById(id);
  }
}
