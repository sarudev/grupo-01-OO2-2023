package com.oo2.grupo01.controllers.account;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccountLogout {
  public ResponseEntity<?> logout(@CookieValue(value = "JWT", required = false) Cookie jwtCookie,
      HttpServletResponse response) {
    if (jwtCookie != null) {
      jwtCookie.setValue("");
      response.addCookie(jwtCookie);
    }
    return ResponseEntity.ok().body("{\"message\": \"Logged out\"}");
  }
}
