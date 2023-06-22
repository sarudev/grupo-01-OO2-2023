package com.oo2.grupo01.entities.enums;

public enum UserRole {
  USER("user"),
  ADMIN("admin");

  private final String text;

  UserRole(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
