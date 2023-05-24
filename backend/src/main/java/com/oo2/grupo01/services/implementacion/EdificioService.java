package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.services.IEdificioService;

@Service("edificoService")
public class EdificioService implements IEdificioService{

	@Override
	public boolean lucesEncendidas() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaAbierto() {
		// TODO Auto-generated method stub
		return false;
	}

}
