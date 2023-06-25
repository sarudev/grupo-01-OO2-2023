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
import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.dto.MessageDTO;
import com.oo2.grupo01.models.Nombre;
import com.oo2.grupo01.models.SensorType;
import com.oo2.grupo01.services.AulaService;
import com.oo2.grupo01.services.EdificioService;
import com.oo2.grupo01.services.SensorService;

@RestController
@RequestMapping("/edificio/{idLugar}/aula")
public class AulaController {
  @Autowired
  EdificioService edificioService;

  @Autowired
  AulaService service;

  @Autowired
  SensorService sensorService;

  @AuthRole("admin")
  @PostMapping
  public ResponseEntity<?> post(@PathVariable("idLugar") String idLugar, @RequestBody Nombre body) {
    Long id;

    try {
      id = Long.parseLong(idLugar);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("'idLugar' it's not a Long"));
    }

    var edif = edificioService.get(id);

    if (edif == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("edificio no existe");

    var aulas = service.getAllById(id);

    for (var a : aulas) {
      if (a.getNombre().equals(body.getNombre()) && a.getLugar().getIdLugar() == id)
        return ResponseEntity.status(HttpStatus.CONFLICT).body("aula ya existe");
    }

    try {
      service.add(edif, body.getNombre());
    } catch (Exception err) {
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll(@PathVariable("idLugar") String idLugar) {
    Long id;

    try {
      id = Long.parseLong(idLugar);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("'idLugar' it's not a Long"));
    }

    var aulas = service.getAllById(id);
    return ResponseEntity.ok(service.toDtoList(aulas));
  }

  @AuthRole("user")
  @GetMapping("/{idDependencia}")
  public ResponseEntity<?> get(@PathVariable("idLugar") String idLugar,
      @PathVariable("idDependencia") String idDependencia) {
    Long idEdificio;
    Long idAula;

    try {
      idEdificio = Long.parseLong(idLugar);
      idAula = Long.parseLong(idDependencia);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorDTO("'idLugar' or 'idDependencia' it's not a Long"));
    }

    var au = service.get(idEdificio, idAula);

    if (au == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Aula not found"));
    }

    return ResponseEntity.ok(service.toDto(au));
  }

  @AuthRole("user")
  @GetMapping("/sensor")
  public ResponseEntity<?> getSensores() {
    return ResponseEntity.ok(AulaDTO.allowedSensores);
  }

  @AuthRole("admin")
  @PostMapping("/{idDependencia}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("idLugar") String idLugar,
      @PathVariable("idDependencia") String idDependencia, @RequestBody SensorType body) {
    Long idEdificio;
    Long idAula;

    try {
      idEdificio = Long.parseLong(idLugar);
      idAula = Long.parseLong(idDependencia);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorDTO("'idLugar' or 'idDependencia' it's not a Long"));
    }

    if (!AulaDTO.allowedSensores.contains(body.getSensorTipo())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("invalid sensor for this aula"));
    }

    var lugar = service.get(idEdificio, idAula);

    if (lugar == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("lugar is null"));
    }

    for (var s : lugar.getSensores()) {
      if (s.getTipo().equals(body)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorDTO("ese sensor ya existe en este edificio"));
      }
    }

    sensorService.add(lugar, body.getSensorTipo());

    return ResponseEntity.status(HttpStatus.OK)
        .body(new MessageDTO("ok"));
  }
}
