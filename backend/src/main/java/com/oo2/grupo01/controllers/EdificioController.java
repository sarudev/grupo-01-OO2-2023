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
import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.dto.ErrorDTO;
import com.oo2.grupo01.repositories.IEdificioRepository;
import com.oo2.grupo01.services.AulaService;
import com.oo2.grupo01.services.EdificioService;

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
  EdificioService edificioService;

  @Autowired
  AulaService aulaService;

  @Autowired
  IEdificioRepository repo;

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll() {
    var edificios = edificioService.getAll();

    return ResponseEntity.ok(edificioService.toDtoList(edificios));
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

    var ed = edificioService.get(id);

    if (ed == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Edificio not found"));
    }

    var aulas = aulaService.getAllById(id);
    ed.setAulas(aulas);

    return ResponseEntity.ok(new EdificioDTO(ed, true));
  }

  @AuthRole("admin")
  @PostMapping("/{idLugar}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("nombreLugar") String nombreLugar) {
    return ResponseEntity.ok("admin post sensor edificio");
  }
}
