package com.oo2.grupo01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.entities.enums.Lugares;
import com.oo2.grupo01.services.implementacion.EdificioService;

import jakarta.servlet.http.HttpServletResponse;

@Controller("edificioController")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/edificio")
public class EdificioController {
  @Autowired
  EdificioService edificioService;

  public EdificioController(EdificioService edificioService) {
    this.edificioService = edificioService;
  }

  @GetMapping
  public ResponseEntity<?> getEdificioAll() {
    System.out.println(Lugares.AULA);
    return ResponseEntity.ok(edificioService.traerTodos());
  }

  @GetMapping("/{buildingName}")
  public ResponseEntity<?> getBuilding(@PathVariable("buildingName") String buildingName,
      HttpServletResponse response) {
    System.out.println(buildingName);
    // if ("") {

    // }

    return ResponseEntity.ok("");
  }
}
