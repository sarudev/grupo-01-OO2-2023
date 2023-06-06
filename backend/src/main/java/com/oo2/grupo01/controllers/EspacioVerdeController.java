package com.oo2.grupo01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oo2.grupo01.services.implementacion.EspacioVerdeService;
import com.oo2.grupo01.services.implementacion.RegistroService;
import com.oo2.grupo01.services.implementacion.SensorService;

import lombok.AllArgsConstructor;

@Controller("espacioVerdeController")
@RequestMapping("/espacioverde")
@AllArgsConstructor
public class EspacioVerdeController {
	private EspacioVerdeService espacioVerdeService;
	private SensorService sensorService;
	private RegistroService registroService;
}
