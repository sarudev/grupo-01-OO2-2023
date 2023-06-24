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
import com.oo2.grupo01.services.implementacion.AulaService;
import com.oo2.grupo01.services.implementacion.EdificioService;

@RestController
@RequestMapping("/edificio/{idLugar}/aula")
public class AulaController {
  @Autowired
  EdificioService edservice;

  @Autowired
  AulaService service;

  @AuthRole("admin")
  @PostMapping
  public ResponseEntity<?> post(@PathVariable("nombreLugar") String nombreLugar, @RequestBody String nombre) {
    // var lugar = Util.urlToName(nombreLugar);

    // var edif = edservice.get(lugar);

    // if (edif == null)
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("edificio no
    // existe");

    // // var aulas = service.getAll();

    // // for (var a : aulas) {
    // // if (a.getNombre().equals(nombre) &&
    // a.getLugar().getNombre().equals(nombre))
    // // return ResponseEntity.status(HttpStatus.CONFLICT).body("aula ya existe");
    // // }

    // try {
    // service.add(edif, lugar);
    // } catch (Exception err) {
    // System.out.println("error?");
    // }

    // var aula = service.get(nombreLugar, nombre);

    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll(@PathVariable("idLugar") String idLugar) {
    // Long id;

    // try {
    // id = Long.parseLong(idLugar);
    // } catch (NumberFormatException exception) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
    // ErrorDTO("'idLugar' it's not a Long"));
    // }

    // var aulas = service.getAll(id);

    // return ResponseEntity.ok(service.toTdoList(aulas));
    return ResponseEntity.ok(service.getAll());
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

  @AuthRole("admin")
  @PostMapping("/{idDependencia}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("nombreLugar") String nombreLugar,
      @PathVariable("nombreDependencia") String nombreDependencia) {
    return ResponseEntity.ok("admin post sensor aula");
  }
}
