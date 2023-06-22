package com.oo2.grupo01.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo01.annotations.AuthRole;

@RestController
@RequestMapping("/sensor")
public class SensorController {
  @AuthRole("admin")
  @PutMapping
  public ResponseEntity<?> toggle(@RequestBody boolean activo) {
    return ResponseEntity.ok("admin put sensor toggle");
  }
}
