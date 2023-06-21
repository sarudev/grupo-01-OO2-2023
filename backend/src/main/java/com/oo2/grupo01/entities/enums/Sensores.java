package com.oo2.grupo01.entities.enums;

public enum Sensores {
  BASCULA("bascula"),
  CAMARA("camara"),
  HUMEDAD("humedad"),
  TIEMPO("tiempo"),
  TEMPERATURA("temperatura");

  private final String text;

  Sensores(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
