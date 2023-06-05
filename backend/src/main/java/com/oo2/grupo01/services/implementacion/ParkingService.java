package com.oo2.grupo01.services.implementacion;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.dto.ParkingDTO;
import com.oo2.grupo01.entities.Parking;
import com.oo2.grupo01.mapeos.ParkingMapeo;
import com.oo2.grupo01.repositories.IParkingRepository;
import com.oo2.grupo01.services.IParkingService;

import lombok.AllArgsConstructor;

@Service("parkingService")
@AllArgsConstructor
public class ParkingService implements IParkingService {
	private IParkingRepository repository;

	@Override
	public void agregar(Parking parking) {
		if (parking != null) {
			repository.save(parking);
		}
	}

	@Override
	public void eliminar(Long id) {
		repository.deleteById(id);
	}

	@Override
	public ParkingDTO traerConDependencias(Long id) {
		Parking parking = repository.traerConDependencias(id).orElse(null);
		return new ParkingDTO(parking);
	}

	@Override
	public List<ParkingDTO> traerTodos() {
		// TODO Auto-generated method stub
		return ParkingMapeo.toDtoList(repository.findAll());
	}
}
