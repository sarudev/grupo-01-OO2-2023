package com.oo2.grupo01.controllers.account;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.oo2.grupo01.Utils.JWT;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AccountUserData {
  public ResponseEntity<?> userData(HttpServletRequest req) throws Exception {
    var cookie = JWT.getJwt(req);
    var payload = JWT
        .toMap(JWT.decodeJWTpayload(cookie.getValue()));

    return ResponseEntity.ok(payload);
  }
}
