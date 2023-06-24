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
import com.oo2.grupo01.services.EspacioVerdeService;

@RestController
@RequestMapping("/espacioVerde")
public class EspacioVerdeController {
  @Autowired
  EspacioVerdeService espacioVerdeService;

  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll() {
    var esp = espacioVerdeService.getAll();

    return ResponseEntity.ok(espacioVerdeService.toDtoList(esp));
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

    var esp = espacioVerdeService.get(id);

    return ResponseEntity.ok(espacioVerdeService.toDto(esp));
  }

  @AuthRole("admin")
  @PostMapping("/{idLugar}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("nombreLugar") String nombreLugar) {
    return ResponseEntity.ok("admin post sensor espacioVerde");
  }
}
