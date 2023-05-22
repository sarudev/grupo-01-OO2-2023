package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.services.IAulaService;

@Service("aulaService")
public class AulaService implements IAulaService{

	@Override
	public boolean hayGente() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lucesEncendidas() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cortinasAbiertas() {
		// TODO Auto-generated method stub
		return false;
	}

}
