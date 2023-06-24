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

import com.oo2.grupo01.Utils.Util;
import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.services.implementacion.AulaService;
import com.oo2.grupo01.services.implementacion.EdificioService;

@RestController
@RequestMapping("/edificio/{nombreLugar}/aula")
public class AulaController {
  @Autowired
  EdificioService edservice;

  @Autowired
  AulaService service;

  @AuthRole("admin")
  @PostMapping
  public ResponseEntity<?> post(@PathVariable("nombreLugar") String nombreLugar, @RequestBody String nombre) {
    var lugar = Util.urlToName(nombreLugar);

    var edif = edservice.get(lugar);

    if (edif == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("edificio no existe");

    var aulas = service.getAll(lugar);

    for (var a : aulas) {
      if (a.getNombre().equals(nombre))
        return ResponseEntity.status(HttpStatus.CONFLICT).body("aula ya existe");
    }

    try {
      service.add(edif, lugar);
    } catch (Exception err) {
      System.out.println("error?");
    }

    var aula = service.get(nombreLugar, nombre);

    return ResponseEntity.status(HttpStatus.CREATED).body(aula);
  }

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @AuthRole("user")
  @GetMapping("/{nombreDependencia}")
  public ResponseEntity<?> get(@PathVariable("nombreLugar") String nombreLugar,
      @PathVariable("nombreDependencia") String nombreDependencia) {
    var ed = edservice.get(Util.urlToName(nombreLugar));
    if (ed == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("edificio no encontrado");
    try {
      var au = service.get(Util.urlToName(nombreLugar), Util.urlToName(nombreDependencia));
      System.out.println("aula: " + au);
      return ResponseEntity.ok(au);
    } catch (Exception err) {
      System.out.println(err);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
    }
  }

  @AuthRole("admin")
  @PostMapping("/{nombreDependencia}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("nombreLugar") String nombreLugar,
      @PathVariable("nombreDependencia") String nombreDependencia) {
    return ResponseEntity.ok("admin post sensor aula");
  }
}
