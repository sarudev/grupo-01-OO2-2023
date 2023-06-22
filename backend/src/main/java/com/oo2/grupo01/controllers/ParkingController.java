package com.oo2.grupo01.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.annotations.AuthRole;

@RestController
@RequestMapping("/parking")
public class ParkingController {
  @AuthRole("user")
  @GetMapping
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok("user get parking");
  }

  @AuthRole("user")
  @GetMapping("/{nombreLugar}")
  public ResponseEntity<?> get(@PathVariable("nombreLugar") String nombreLugar) {
    return ResponseEntity.ok("user get all parking");
  }

  @AuthRole("admin")
  @PostMapping("/{nombreLugar}/sensor")
  public ResponseEntity<?> sensor(@PathVariable("nombreLugar") String nombreLugar) {
    return ResponseEntity.ok("admin post sensor parking");
  }
}
