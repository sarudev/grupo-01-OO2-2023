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
import com.oo2.grupo01.dto.MessageDTO;
import com.oo2.grupo01.dto.ParkingDTO;
import com.oo2.grupo01.services.EstacionamientoService;
import com.oo2.grupo01.services.ParkingService;
import com.oo2.grupo01.services.SensorService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
  @Autowired
  ParkingService service;

  @Autowired
  EstacionamientoService estacionamientoService;

  @Autowired
  SensorService sensorService;

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll() {
    var parking = service.getAll();

    return ResponseEntity.ok(service.toDtoList(parking));
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

    var parking = service.get(id);

    if (parking == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Edificio not found"));
    }

    return ResponseEntity.ok(service.toDto(parking));
  }

  @AuthRole("admin")
  @GetMapping("/{idDependencia}/sensor")
  public ResponseEntity<?> getSensores() {
    return ResponseEntity.ok(ParkingDTO.allowedSensores);
  }

  @AuthRole("admin")
  @PostMapping("/{idLugar}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("idLugar") String idLugar, @RequestBody String tipo) {
    Long id;

    try {
      id = Long.parseLong(idLugar);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("'idLugar' it's not a Long"));
    }

    if (SensorUtil.isSensorTipo(tipo)) {
      var sensorTipo = SensorUtil.toSensorTipo(tipo);

      if (!ParkingDTO.allowedSensores.contains(sensorTipo)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("invalid sensor for this parking"));
      }

      var lugar = service.get(id);

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
