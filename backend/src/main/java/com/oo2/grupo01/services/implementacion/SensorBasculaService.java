package com.oo2.grupo01.services.implementacion;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.services.ISensorBasculaService;

@Service("sensorBasculaService")
public class SensorBasculaService implements ISensorBasculaService{

	@Override
	public double pesoLimite() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double pesoActual() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean superoLimite() {
		// TODO Auto-generated method stub
		return false;
	}

}
