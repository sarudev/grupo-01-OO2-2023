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

import com.oo2.grupo01.Utils.SensorUtil;
import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.dto.EstacionamientoDTO;
import com.oo2.grupo01.dto.MessageDTO;
import com.oo2.grupo01.services.EstacionamientoService;
import com.oo2.grupo01.services.ParkingService;
import com.oo2.grupo01.services.SensorService;

@RestController
@RequestMapping("/parking/{idLugar}/estacionamiento")
public class EstacionamientoController {
  @Autowired
  ParkingService parkingService;

  @Autowired
  EstacionamientoService service;

  @Autowired
  SensorService sensorService;

  @AuthRole("admin")
  @PostMapping
  public ResponseEntity<?> post(@PathVariable("idLugar") String idLugar, @RequestBody String nombre) {
    Long id;

    try {
      id = Long.parseLong(idLugar);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("'idLugar' it's not a Long"));
    }

    var parking = parkingService.get(id);

    if (parking == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("parking no existe");

    var estacionamientos = service.getAllById(id);

    for (var est : estacionamientos) {
      System.out.println(est.getNombre());
      System.out.println(est.getNombre());
      if (est.getNombre().equals(nombre) && est.getLugar().getIdLugar() == id)
        return ResponseEntity.status(HttpStatus.CONFLICT).body("estacionamiento ya existe");
    }

    try {
      service.add(parking, nombre);
    } catch (Exception err) {
      System.out.println("error?");
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
    Long idParking;
    Long idEstacionamiento;

    try {
      idParking = Long.parseLong(idLugar);
      idEstacionamiento = Long.parseLong(idDependencia);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorDTO("'idLugar' or 'idDependencia' it's not a Long"));
    }

    var est = service.get(idParking, idEstacionamiento);

    if (est == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Estacionamiento not found"));
    }

    return ResponseEntity.ok(service.toDto(est));
  }

  @AuthRole("admin")
  @GetMapping("/{idDependencia}/sensor")
  public ResponseEntity<?> getSensores() {
    return ResponseEntity.ok(EstacionamientoDTO.allowedSensores);
  }

  @AuthRole("admin")
  @PostMapping("/{idDependencia}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("idLugar") String idLugar,
      @PathVariable("idDependencia") String idDependencia, @RequestBody String tipo) {
    Long idParking;
    Long idEstacionamiento;

    try {
      idParking = Long.parseLong(idLugar);
      idEstacionamiento = Long.parseLong(idDependencia);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorDTO("'idLugar' or 'idDependencia' it's not a Long"));
    }

    if (SensorUtil.isSensorTipo(tipo)) {
      var sensorTipo = SensorUtil.toSensorTipo(tipo);

      if (!EstacionamientoDTO.allowedSensores.contains(sensorTipo)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("invalid sensor for this estacionamiento"));
      }

      var lugar = service.get(idParking, idEstacionamiento);

      if (lugar == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("lugar is null"));
      }

      for (var s : lugar.getSensores()) {
        if (s.getTipo().equals(sensorTipo)) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(new ErrorDTO("ese sensor ya existe en este edificio"));
        }
      }

      sensorService.add(lugar, sensorTipo);

      return ResponseEntity.status(HttpStatus.OK)
          .body(new MessageDTO("ok"));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("tipo de sensor no valido"));
    }
  }
}
