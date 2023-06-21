package com.oo2.grupo01.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.Edificio;
import com.oo2.grupo01.entities.Lugares;
import com.oo2.grupo01.entities.Sensores;
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
	
	@PostMapping
	public ResponseEntity<Object> crearEdificio(@RequestBody String nombre) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		try {
			edificioService.agregar(Lugares.EDIFICIO, nombre);
			httpStatus = HttpStatus.CREATED;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(httpStatus);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Object> crearEdificio(@PathVariable(name = "id") Long idEdificio,
			@RequestBody String nombre) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		try {
			aulaService.agregar(Lugares.AULA, edificioService.traer(idEdificio), nombre);
			httpStatus = HttpStatus.CREATED;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(httpStatus);
	}
	
	@GetMapping
	public ResponseEntity<List<EdificioDTO>> traerTodosLosEdificios() {
		return new ResponseEntity<List<EdificioDTO>>(edificioService.traerTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EdificioDTO> traerEdificio(@PathVariable(name = "id") Long id) {
		Edificio edificio = edificioService.traerConDependencias(id); 
		EdificioDTO eDTO = null;

		if (edificio != null) {
			eDTO = new EdificioDTO(edificio);
			eDTO.inicializarVariables();
			registroService.agregar(eDTO.getSensores());
		}

		return new ResponseEntity<EdificioDTO>(eDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{idEdificio}/{idAula}")
	public ResponseEntity<AulaDTO> traerAula(
			@PathVariable(name = "idEdificio") Long idEdificio,
			@PathVariable(name = "idAula") Long idAula) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		AulaDTO aDTO = null;

		if (edificioService.traer(idEdificio) != null) {
			Aula aula = aulaService.traerConDependencias(idAula);
			if (aula!= null) {
				aDTO = new AulaDTO(aula);
				aDTO.inicializarVariables();
				registroService.agregar(aDTO.getSensores());
			}
		}

		return new ResponseEntity<AulaDTO>(aDTO, httpStatus);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminarEdificio(@PathVariable(name = "id") Long id) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		try {
			edificioService.eliminar(id);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(httpStatus);
	}
	
	@DeleteMapping("/{id}/{idAula}")
	public ResponseEntity<Object> eliminarAula(@PathVariable(name = "id") Long id,
			@PathVariable(name = "id") Long idAula) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		try {
			aulaService.eliminar(idAula);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(httpStatus);
	}
	
	@PostMapping("/{idEdificio}/sensor")
	public ResponseEntity<Object> agregarSensorAEdificio(@PathVariable(name = "idEdificio") Long id,
			@RequestBody Sensores tipo) {
		Edificio edificio = edificioService.traer(id);
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		try {
			sensorService.agregar(tipo, edificio);
			httpStatus = HttpStatus.CREATED;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(httpStatus);
	}
	
	@PostMapping("/{idEdificio}/{idAula}/sensor")
	public ResponseEntity<Object> agregarSensorAAula(@PathVariable(name = "idEdificio") Long idEdificio,
			@PathVariable(name = "idAula") Long idAula, @RequestBody Sensores tipo) {
		Aula aula = aulaService.traer(idAula);
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		try {
			sensorService.agregar(tipo, aula);
			httpStatus = HttpStatus.CREATED;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(httpStatus);
	}
	
}
