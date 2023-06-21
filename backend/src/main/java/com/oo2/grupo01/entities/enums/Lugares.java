package com.oo2.grupo01.entities.enums;

public enum Lugares {
  AULA("aula"),
  EDIFICIO("edificio"),
  ESPACIO_VERDE("espacioVerde"),
  ESTACIONAMIENTO("estacionamiento"),
  PARKING("parking");

  private final String text;

  Lugares(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
