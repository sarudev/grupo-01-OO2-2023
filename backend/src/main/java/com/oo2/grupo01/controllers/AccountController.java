package com.oo2.grupo01.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oo2.grupo01.entities.User;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.Cookie;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired
  private LoginController loginController;

  @Autowired
  private LogoutController logoutController;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
    return loginController.login(user, response);
  }

  @GetMapping("/userData")
  public ResponseEntity<?> userData(@CookieValue(value = "JWT", required = false) Cookie jwtCookie,
      HttpServletResponse response) {
    if (jwtCookie == null || jwtCookie.getValue() == "") {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"Token missing\"}");
    }

    String[] chunks = jwtCookie.getValue().split("\\.");

    Base64.Decoder decoder = Base64.getUrlDecoder();

    String payload = new String(decoder.decode(chunks[1]));

    return ResponseEntity.ok(payload);
  }

  @GetMapping("/logout")
  public ResponseEntity<?> logout(@CookieValue(value = "JWT", required = false) Cookie jwtCookie,
      HttpServletResponse response) {
    return logoutController.logout(jwtCookie, response);
  }

}
