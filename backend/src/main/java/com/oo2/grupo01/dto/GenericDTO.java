package com.oo2.grupo01.dto;

import java.util.Set;

import com.oo2.grupo01.entities.Lugares;
import com.oo2.grupo01.entities.Sensor;

import lombok.Getter;

@Getter
public abstract class GenericDTO<T> {
  Long id;
  Lugares type;
  Set<Sensor> sensores;

  protected GenericDTO(Long id, Lugares type, Set<Sensor> sensores) {
    this.id = id;
    this.type = type;
    this.sensores = sensores;
  }
}
