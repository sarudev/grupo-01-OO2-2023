package com.oo2.grupo01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oo2.grupo01.services.implementacion.AulaService;
import com.oo2.grupo01.services.implementacion.EdificioService;
import com.oo2.grupo01.services.implementacion.RegistroService;
import com.oo2.grupo01.services.implementacion.SensorService;

import lombok.AllArgsConstructor;

@Controller("edificioController")
@RequestMapping("/edificios")
@AllArgsConstructor
public class EdificioController {
	private EdificioService edificioService;
	private AulaService aulaService;
	private SensorService sensorService;
	private RegistroService registroService;
}
