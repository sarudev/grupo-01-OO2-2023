package com.oo2.grupo01.interceptors;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo01.entities.UserRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Key;
import java.nio.charset.StandardCharsets;

public class AuthRoleInterceptor implements HandlerInterceptor  {

	   private static final String JWT_SECRET = System.getenv("JWT_SECRET");

	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        String jwtoken = request.getHeader("Authorization");
	        if (jwtoken == null || !jwtoken.startsWith("Bearer ")) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            return false;
	        }
	        jwtoken = jwtoken.substring(7); // Remove "Bearer " prefix
	        try {
	            Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
	            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtoken).getBody();
	            String role = claims.get("role", String.class);
	            UserRole userRole = UserRole.valueOf(role);
	            if (userRole == null || userRole == UserRole.ADMIN) {
	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                return false;
	            }
	        } catch (JwtException e) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            return false;
	        }
	        return true;
	    }

	 @Override
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	     // Implementación opcional
	     // Llamar al método next() para permitir que el flujo de ejecución continúe
	     HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	 }

	 @Override
	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	     // Implementación opcional
	 }
	
}
