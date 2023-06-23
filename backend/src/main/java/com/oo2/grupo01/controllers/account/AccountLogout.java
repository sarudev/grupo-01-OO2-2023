package com.oo2.grupo01.controllers.account;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.oo2.grupo01.Utils.JWT;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccountLogout {
  public ResponseEntity<?> logout(HttpServletRequest req, HttpServletResponse res) {
    Cookie jwtCookie = JWT.getJwt(req);

    if (jwtCookie != null) {
      // Crear una nueva cookie con el mismo nombre y valor, pero con tiempo de vida 0
      Cookie deletedCookie = new Cookie(jwtCookie.getName(), jwtCookie.getValue());
      deletedCookie.setMaxAge(0);
      deletedCookie.setPath("/"); // Establecer el mismo path en el que se creó la cookie

      // Agregar la nueva cookie al objeto HttpServletResponse
      res.addCookie(deletedCookie);
    }

    return ResponseEntity.ok().body("{\"message\": \"Logged out\"}");
  }
}
