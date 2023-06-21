package com.oo2.grupo01.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.repositories.IUserRepository;
import com.oo2.grupo01.services.implementacion.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CookieValue;
import jakarta.servlet.http.Cookie;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final IUserRepository userRepository;
    private final UserService userService;

    @Autowired
    public AccountController(IUserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Autowired
    private LoginController loginController;

    @Autowired
    private UserRoleController userRoleController;

    @Autowired
    private LogoutController logoutController;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
        return loginController.login(user, response);
    }

    @GetMapping("/userRole/{username}")
    public ResponseEntity<?> getUserRole(@PathVariable String username) {
        return userRoleController.getUserRole(username);
    }

    @GetMapping("/userData")
    public ResponseEntity<?> userData(@CookieValue(value = "JWT", required = false) Cookie jwtCookie) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "JWT", required = false) Cookie jwtCookie, HttpServletResponse response) {
        return logoutController.logout(jwtCookie, response);
    }
    
    
}