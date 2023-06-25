package com.oo2.grupo01.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:5173") // Reemplaza con la URL de tu frontend
        .allowedMethods("GET", "POST", "PUT", "DELETE") // Agrega los métodos HTTP permitidos
        .allowedHeaders("*") // Permite todos los encabezados
        .allowCredentials(true); // Permite enviar cookies
  }
}
