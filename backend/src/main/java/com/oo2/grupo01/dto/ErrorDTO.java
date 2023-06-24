package com.oo2.grupo01.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
  private String error;

  public ErrorDTO(String message) {
    this.error = message;
  }
}
