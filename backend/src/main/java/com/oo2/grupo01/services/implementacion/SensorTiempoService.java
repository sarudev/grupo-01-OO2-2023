package com.oo2.grupo01.services.implementacion;

import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.services.ISensorTiempoService;

@Service("sensorTiempoService")
public class SensorTiempoService implements ISensorTiempoService{

	@Override
	public LocalTime horaActual() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hayLuzSolar() {
		// TODO Auto-generated method stub
		return false;
	}

}
