package com.oo2.grupo01.Utils;

import org.springframework.stereotype.Component;

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
}
