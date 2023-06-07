package com.oo2.grupo01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oo2.grupo01.services.implementacion.SensorService;

import lombok.AllArgsConstructor;

@Controller("sensorController")
@RequestMapping("/sensor")
@AllArgsConstructor
public class SensorController {

	private SensorService sensorService;
	
	/*
	 * Queda a implementar, para más practicidad, las funcionalidades:
	 * --Eliminar sensor
	 * --Switch sensor
	 * 
	 * Además de tener en cuenta un posible traerConRegistros(Long id) para
	 * una eventual página de un sensor x con todo lo que registró hasta el momento.
	 * 
	 * Los agregar sensor se harán en los otros controllers porque esos ya disponen de los
	 * services de los posibles lugares a los que puede ser agregado un sensor
	 * */
	
}
