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
import com.oo2.grupo01.services.AulaService;
import com.oo2.grupo01.services.EdificioService;

@RestController
@RequestMapping("/edificio/{idLugar}/aula")
public class AulaController {
  @Autowired
  EdificioService edificioService;

  @Autowired
  AulaService aulaService;

  @AuthRole("admin")
  @PostMapping
  public ResponseEntity<?> post(@PathVariable("idLugar") String idLugar, @RequestBody String nombre) {
    Long id;

    try {
      id = Long.parseLong(idLugar);
    } catch (NumberFormatException exception) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("'idLugar' it's not a Long"));
    }

    var edif = edificioService.get(id);

    if (edif == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("edificio no existe");

    var aulas = aulaService.getAllById(id);

    for (var a : aulas) {
      if (a.getNombre().equals(nombre) && a.getLugar().getIdLugar() == id)
        return ResponseEntity.status(HttpStatus.CONFLICT).body("aula ya existe");
    }

    try {
      aulaService.add(edif, nombre);
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

    var aulas = aulaService.getAllById(id);
    return ResponseEntity.ok(aulaService.toDtoList(aulas));
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

    var au = aulaService.get(idEdificio, idAula);

    if (au == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Aula not found"));
    }

    return ResponseEntity.ok(au);
    // return ResponseEntity.ok(aulaService.toDto(au));
  }

  @AuthRole("admin")
  @PostMapping("/{idDependencia}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("nombreLugar") String nombreLugar,
      @PathVariable("nombreDependencia") String nombreDependencia) {
    return ResponseEntity.ok("admin post sensor aula");
  }
}
