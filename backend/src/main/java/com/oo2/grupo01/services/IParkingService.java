package com.oo2.grupo01.services;

import java.util.List;

import com.oo2.grupo01.dto.ParkingDTO;
import com.oo2.grupo01.entities.Parking;

public interface IParkingService extends IGenericService<Parking, ParkingDTO> {
	public List<ParkingDTO> traerTodos();

}
