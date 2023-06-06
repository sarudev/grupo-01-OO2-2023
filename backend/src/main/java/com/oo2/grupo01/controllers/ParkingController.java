package com.oo2.grupo01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oo2.grupo01.services.implementacion.EstacionamientoService;
import com.oo2.grupo01.services.implementacion.ParkingService;
import com.oo2.grupo01.services.implementacion.RegistroService;
import com.oo2.grupo01.services.implementacion.SensorService;

import lombok.AllArgsConstructor;

@Controller("parkingController")
@RequestMapping("/parkings")
@AllArgsConstructor
public class ParkingController {
	private ParkingService parkingService;
	private EstacionamientoService estacionamientoService;
	private SensorService sensorService;
	private RegistroService registroService;
}
