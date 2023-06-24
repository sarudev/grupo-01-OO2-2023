package com.oo2.grupo01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.services.EstacionamientoService;
import com.oo2.grupo01.services.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
  @Autowired
  ParkingService parkingService;

  @Autowired
  EstacionamientoService estacionamientoService;

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll() {
    var parking = parkingService.getAll();

    return ResponseEntity.ok(parkingService.toDtoList(parking));
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

    var parking = parkingService.get(id);

    if (parking == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Edificio not found"));
    }

    var estacionamientos = estacionamientoService.getAllById(id);
    parking.setEstacionamientos(estacionamientos);

    return ResponseEntity.ok(parkingService.toDto(parking));
  }

  @AuthRole("admin")
  @PostMapping("/{idLugar}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("idLugar") String idLugar) {
    return ResponseEntity.ok("admin post sensor parking");
  }
}
