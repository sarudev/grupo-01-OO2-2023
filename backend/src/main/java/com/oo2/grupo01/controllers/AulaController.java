package com.oo2.grupo01.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.annotations.AuthRole;

@RestController
@RequestMapping("/edificio/{nombreLugar}/aula")
public class AulaController {
  @AuthRole("admin")
  @PostMapping
  public ResponseEntity<?> post(@PathVariable("nombreLugar") String nombreLugar) {
    return ResponseEntity.ok("admin post aula");
  }

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll(@PathVariable("nombreLugar") String nombreLugar) {
    return ResponseEntity.ok("user get all aula");
  }

  @AuthRole("user")
  @GetMapping("/{nombreDependencia}")
  public ResponseEntity<?> get(@PathVariable("nombreLugar") String nombreLugar,
      @PathVariable("nombreDependencia") String nombreDependencia) {
    return ResponseEntity.ok("user get aula");
  }

  @AuthRole("admin")
  @PostMapping("/{nombreDependencia}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("nombreLugar") String nombreLugar,
      @PathVariable("nombreDependencia") String nombreDependencia) {
    return ResponseEntity.ok("admin post sensor aula");
  }
}
