package com.oo2.grupo01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.services.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {
  @Autowired
  SensorService sensorService;

  @AuthRole("admin")
  @PutMapping("/{idSensor}")
  public ResponseEntity<?> toggle(@PathVariable("idSensor") String idSensor) {
    Long id;

    try {
      id = Long.parseLong(idSensor);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("'idSensor' it's not a Long"));
    }
    var sensor = sensorService.get(id);

    if (sensor == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ErrorDTO("'sensor' with id " + idSensor + " was not found"));
    }

    sensorService.toggle(sensor);

    return ResponseEntity.ok("toggled");
  }
}
