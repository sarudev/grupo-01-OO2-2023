package com.oo2.grupo01.mapeos;

import java.util.ArrayList;
import java.util.List;

import com.oo2.grupo01.dto.ParkingDTO;
import com.oo2.grupo01.entities.Parking;

public class ParkingMapeo {
	// List<Parking> ----> List<ParkingDto>
	public static List<ParkingDTO> toDtoList(List<Parking> listEntity) {
		List<ParkingDTO> parkings = new ArrayList<ParkingDTO>();

		for (Parking p : listEntity) {
			parkings.add(new ParkingDTO(p));
		}

		return parkings;
	}
}
