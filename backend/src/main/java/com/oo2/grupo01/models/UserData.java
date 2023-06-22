package com.oo2.grupo01.models;

import java.util.Base64;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Getter
public class UserData {
  private int id;
  private String username;
  private String role;

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

  static public Integer roleToValue(String role) {
    if (role.toLowerCase().equals("admin"))
      return 1;
    if (role.toLowerCase().equals("user"))
      return 0;
    return null;
  }
}
