package com.oo2.grupo01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.repositories.IEdificioRepository;
import com.oo2.grupo01.services.implementacion.EdificioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/edificio")
public class EdificioController {
  @Autowired
  HttpServletRequest req;

  @Autowired
  HttpServletResponse res;

  @Autowired
  EdificioService service;

  @Autowired
  IEdificioRepository repo;

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @AuthRole("user")
  @GetMapping("/{nombreLugar}")
  public ResponseEntity<?> get(@PathVariable("nombreLugar") String nombreLugar) {
    return ResponseEntity.ok(service.get(nombreLugar.replaceAll("-", " ")));
  }

  @AuthRole("admin")
  @PostMapping("/{nombreLugar}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("nombreLugar") String nombreLugar) {
    return ResponseEntity.ok("admin post sensor edificio");
  }
}
