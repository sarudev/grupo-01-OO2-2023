package com.oo2.grupo01.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oo2.grupo01.services.implementacion.SensorService;

import lombok.AllArgsConstructor;

@Controller("sensorController")
@RequestMapping("/sensor")
@AllArgsConstructor
public class SensorController {

	private SensorService sensorService;
	
	/*
	 * Además de tener en cuenta un posible traerConRegistros(Long id) para
	 * una eventual página de un sensor x con todo lo que registró hasta el momento.
	 * 
	 * Los agregar sensor se harán en los otros controllers porque esos ya disponen de los
	 * services de los posibles lugares a los que puede ser agregado un sensor
	 * */
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarSensor(@PathVariable(name = "id")Long id){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		try {
			sensorService.eliminar(id);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(httpStatus);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Object> switchSensor(@PathVariable(name = "id")Long id){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		try {
			sensorService.switchSensor(id);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(httpStatus);
	}
}
