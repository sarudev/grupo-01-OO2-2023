package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.services.IParkingService;

@Service("parkingService")
public class ParkingService implements IParkingService{

	@Override
	public boolean lucesEncendidas() {
		// TODO Auto-generated method stub
		return false;
	}

}
