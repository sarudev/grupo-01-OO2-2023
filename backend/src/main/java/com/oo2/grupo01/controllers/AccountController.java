package com.oo2.grupo01.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oo2.grupo01.Utils.JWT;
import com.oo2.grupo01.annotations.AuthRole;
import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
	private final HttpServletRequest req;
	private final HttpServletResponse res;
	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();

		User storedUser = userService.findByUsername(username);

		if (storedUser == null || !userService.isPasswordCorrect(storedUser, password)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}

		String token = JWT.generateToken(storedUser);

		Cookie jwtCookie = new Cookie("JWT", token);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setPath("/");

		res.addCookie(jwtCookie);

		return ResponseEntity.status(HttpStatus.OK).body("login");
	}

	@AuthRole("user")
	@GetMapping("/userData")
	public ResponseEntity<?> userData() throws Exception {
		var cookie = JWT.getJwt(req);
		var payload = JWT.toMap(JWT.decodeJWTpayload(cookie.getValue()));

		return ResponseEntity.ok(payload);
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
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
