package com.oo2.grupo01.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/logout")
public class LogoutController {
    @GetMapping
    public ResponseEntity<?> logout(@CookieValue(value = "JWT", required = false) Cookie jwtCookie, HttpServletResponse response) {
        if (jwtCookie != null) {
            jwtCookie.setMaxAge(0); // Set cookie expiration to 0 to delete it
            response.addCookie(jwtCookie);
        }
        return ResponseEntity.ok().body("{\"message\": \"Logged out\"}");
    }
}
