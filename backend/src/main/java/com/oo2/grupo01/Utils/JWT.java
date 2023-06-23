package com.oo2.grupo01.Utils;

import java.security.Key;
import java.util.Base64;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oo2.grupo01.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWT {
  public static Cookie getJwt(HttpServletRequest req) {
    var cookies = req.getCookies();

    if (cookies == null || cookies.length == 0)
      return null;

    Cookie jwtCookieValue = null;
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("JWT") || cookie.getName().equals("jwt")) {
        jwtCookieValue = cookie;
        break;
      }
    }
    return jwtCookieValue;
  }

  public static String generateToken(User user) {
    Claims claims = Jwts.claims();
    claims.put("id", user.getId());
    claims.put("username", user.getUsername());
    claims.put("role", user.getRole());

    byte[] keyBytes = "secret_excesivamente_largo_para_que_spring_no_rompa_las_bolas_con_el_largo_de_los_bytes"
        .getBytes();
    Key key = Keys.hmacShaKeyFor(keyBytes);

    return Jwts.builder()
        .setClaims(claims)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  static public Map<String, Object> toMap(String userData) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> mapa = objectMapper.readValue(userData, new TypeReference<Map<String, Object>>() {
    });

    return mapa;
  }

  static public String decodeJWTpayload(String token) {
    String[] chunks = token.split("\\.");

    Base64.Decoder decoder = Base64.getUrlDecoder();

    String payload = new String(decoder.decode(chunks[1]));

    return payload;
  }
}
