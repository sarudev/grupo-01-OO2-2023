package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.services.IEstacionamientoService;


@Service("estacionamientoService")
public class EstacionamientoService implements IEstacionamientoService{

	@Override
	public boolean estaOcupado() {
		// TODO Auto-generated method stub
		return false;
	}

}
