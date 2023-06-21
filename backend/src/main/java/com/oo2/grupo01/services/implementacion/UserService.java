package com.oo2.grupo01.services.implementacion;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.oo2.grupo01.entities.User;
import com.oo2.grupo01.entities.UserRole;
import com.oo2.grupo01.repositories.IUserRepository;
import com.oo2.grupo01.services.IUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service("userService")
public class UserService implements IUserService, UserDetailsService {

  @Autowired
  private IUserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public User findByUsername(String username) {
    System.out.println("Searching user by username: " + username);
    return userRepository.findByUsername(username);
  }

  public boolean isPasswordCorrect(User user, String password) {
    return passwordEncoder.matches(password, user.getPassword());
  }

  public String generateToken(User user) {
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

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    Set<UserRole> userRoles = Collections.singleton(user.getRole()); // Convertir el rol individual en un conjunto
    return buildUser(user, buildGrantedAuthorities(userRoles));
  }

  private org.springframework.security.core.userdetails.User buildUser(User user,
      List<GrantedAuthority> grantedAuthorities) {
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        user.isEnabled(),
        true, true, true, // accountNonExpired, credentialsNonExpired, accountNonLocked,
        grantedAuthorities);
  }

  private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (UserRole userRole : userRoles) {
      grantedAuthorities.add(new SimpleGrantedAuthority(userRole.name()));
    }
    return new ArrayList<>(grantedAuthorities);
  }

}
