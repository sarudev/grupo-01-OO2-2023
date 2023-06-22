package com.oo2.grupo01.controllers.edificio;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EdificioGetAll {
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok("get all edificio");
  }
}
