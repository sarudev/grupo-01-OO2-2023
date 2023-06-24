package com.oo2.grupo01.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.services.EstacionamientoService;
import com.oo2.grupo01.services.LugarService;
import com.oo2.grupo01.services.ParkingService;
import com.oo2.grupo01.services.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {

  @Autowired
  private SensorService sensorService;

  @Autowired
  LugarService lugarService;

  @GetMapping("/listar")
  public List<Sensor> listar() {
    return sensorService.getAll();
  }

  @GetMapping("traer/{idLugar}")
  public ResponseEntity<?> traerPorId(@PathVariable("idLugar") String idLugar) {
    Long id;

    try {
      id = Long.parseLong(idLugar);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ErrorDTO("'idLugar' it's not a Long"));
    }

    var sensores = sensorService.getAllById(id);

    return ResponseEntity.ok(sensores);
  }

  @GetMapping("/{lugarId}/sensores")
  public ResponseEntity<?> getSensoresById(@PathVariable long lugarId) {
    List<Sensor> sensores = lugarService.findSensoresByLugarId(lugarId);
    return ResponseEntity.ok(sensores);
  }

}
