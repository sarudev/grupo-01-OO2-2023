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
import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.services.EstacionamientoService;
import com.oo2.grupo01.services.ParkingService;

@RestController
@RequestMapping("/parking/{idLugar}/estacionamiento")
public class EstacionamientoController {
  @Autowired
  ParkingService parkingService;

  @Autowired
  EstacionamientoService estacionamientoService;

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

    var estacionamientos = estacionamientoService.getAllById(id);

    for (var est : estacionamientos) {
      System.out.println(est.getNombre());
      System.out.println(est.getNombre());
      if (est.getNombre().equals(nombre) && est.getLugar().getIdLugar() == id)
        return ResponseEntity.status(HttpStatus.CONFLICT).body("estacionamiento ya existe");
    }

    try {
      estacionamientoService.add(parking, nombre);
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

    var aulas = estacionamientoService.getAllById(id);
    return ResponseEntity.ok(estacionamientoService.toDtoList(aulas));
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

    var est = estacionamientoService.get(idParking, idEstacionamiento);

    if (est == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Estacionamiento not found"));
    }

    return ResponseEntity.ok(estacionamientoService.toDto(est));
  }

  @AuthRole("admin")
  @PostMapping("/{idDependencia}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("idLugar") String idLugar,
      @PathVariable("idDependencia") String idDependencia) {
    return ResponseEntity.ok("admin post sensor estacionamiento");
  }
}
