package com.oo2.grupo01.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.dto.EspacioVerdeDTO;
import com.oo2.grupo01.dto.MessageDTO;
import com.oo2.grupo01.entities.EspacioVerde;
import com.oo2.grupo01.models.SensorType;
import com.oo2.grupo01.services.EspacioVerdeService;
import com.oo2.grupo01.services.HistorialService;
import com.oo2.grupo01.services.SensorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/espacioVerde")
@RequiredArgsConstructor
public class EspacioVerdeController {
	private final EspacioVerdeService service;
	private final SensorService sensorService;
	private final HistorialService historialService;
	
	@AuthRole("user")
	@GetMapping
	public ResponseEntity<?> getAll() {
		var esp = service.getAll();

		return ResponseEntity.ok(service.toDtoList(esp));
	}

	@AuthRole("user")
	@GetMapping("/{idLugar}")
	public ResponseEntity<?> get(@PathVariable("idLugar") String idLugar) {
		Long id;

		try {
			id = Long.parseLong(idLugar);
		} catch (NumberFormatException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("'idLugar' it's not a Long"));
		}

		EspacioVerde esp = service.get(id);

		if (esp == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("'espacioVerde' not found"));

		EspacioVerdeDTO evDTO = service.toDto(esp);
		historialService.agregarHistorial(esp, evDTO);
		
		return ResponseEntity.ok(evDTO);
	}

	@AuthRole("user")
	@GetMapping("/sensor")
	public ResponseEntity<?> getSensores() {
		return ResponseEntity.ok(EspacioVerdeDTO.allowedSensores);
	}

	@AuthRole("admin")
	@PostMapping("/{idLugar}/sensor")
	public ResponseEntity<?> sensor(@PathVariable("idLugar") String idLugar, @RequestBody SensorType body) {
		Long id;

		try {
			id = Long.parseLong(idLugar);
		} catch (NumberFormatException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("'idLugar' it's not a Long"));
		}

		if (!EspacioVerdeDTO.allowedSensores.contains(body.getSensorTipo())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorDTO("invalid sensor for this espacioVerde"));
		}

		var lugar = service.get(id);

		if (lugar == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("lugar is null"));
		}

		for (var s : lugar.getSensores()) {
			if (s.getTipo().equals(body.getSensorTipo())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ErrorDTO("ese sensor ya existe en este edificio"));
			}
		}

		sensorService.add(lugar, body.getSensorTipo());

		return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("ok"));
	}
}
