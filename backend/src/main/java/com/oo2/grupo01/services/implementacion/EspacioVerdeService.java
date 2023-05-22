package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.services.IEspacioVerdeService;

@Service("espacioVerdeService")
public class EspacioVerdeService implements IEspacioVerdeService{

	@Override
	public boolean lucesEncendidas() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double nivelHumedad() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hayHumedadAlta() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hayHumedadBaja() {
		// TODO Auto-generated method stub
		return false;
	}

}
