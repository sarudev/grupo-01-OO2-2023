package com.oo2.grupo01.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.services.LugarService;
import com.oo2.grupo01.services.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {

  @Autowired
  private SensorService sensorService;

  @Autowired
  private LugarService lugarService;

  @GetMapping("/listar")
  public List<Sensor> listar() {
    return sensorService.findAllSensores();
  }

  @GetMapping("traer/{id}")
  public Optional<Sensor> traerPorId(@PathVariable("id") Long id) {
    return sensorService.findSensorById(id);
  }

  @GetMapping("/{lugarId}/sensores")
  public ResponseEntity<?> getSensoresById(@PathVariable long lugarId) {
    List<Sensor> sensores = lugarService.findSensoresByLugarId(lugarId);
    return ResponseEntity.ok(sensores);
  }

}
