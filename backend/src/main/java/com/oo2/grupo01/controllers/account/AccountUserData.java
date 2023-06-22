package com.oo2.grupo01.controllers.account;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;

import com.oo2.grupo01.models.UserData;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccountUserData {
  public ResponseEntity<?> userData(@CookieValue(value = "JWT", required = false) Cookie jwtCookie,
      HttpServletResponse response, HttpServletRequest request) throws Exception {
    var payload = UserData
        .toMap(UserData.decodeJWTpayload(jwtCookie.getValue()));

    return ResponseEntity.ok(payload);
  }
}
