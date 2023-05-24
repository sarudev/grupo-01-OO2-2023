package com.oo2.grupo01.services;

import java.time.LocalTime;

public interface ISensorTiempoService {

	
	public LocalTime horaActual();
	
	public boolean hayLuzSolar();
}
