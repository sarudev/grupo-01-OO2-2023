package com.oo2.grupo01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.dto.MessageDTO;
import com.oo2.grupo01.models.SensorType;
import com.oo2.grupo01.repositories.IEdificioRepository;
import com.oo2.grupo01.services.AulaService;
import com.oo2.grupo01.services.EdificioService;
import com.oo2.grupo01.services.SensorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/edificio")
public class EdificioController {
  @Autowired
  HttpServletRequest req;

  @Autowired
  HttpServletResponse res;

  @Autowired
  IEdificioRepository repo;

  @Autowired
  EdificioService service;

  @Autowired
  AulaService aulaService;

  @Autowired
  SensorService sensorService;

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll() {
    var edificios = service.getAll();

    return ResponseEntity.ok(service.toDtoList(edificios));
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

    var ed = service.get(id);

    if (ed == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Edificio not found"));
    }

    return ResponseEntity.ok(service.toDto(ed));
  }

  @AuthRole("user")
  @GetMapping("sensor")
  public ResponseEntity<?> getSensores() {
    return ResponseEntity.ok(EdificioDTO.allowedSensores);
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

    if (!EdificioDTO.allowedSensores.contains(body.getSensorTipo())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("invalid sensor for this edificio"));
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

    return ResponseEntity.status(HttpStatus.OK)
        .body(new MessageDTO("ok"));
  }
}
