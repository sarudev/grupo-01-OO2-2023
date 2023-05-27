package com.oo2.grupo01.services;

import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.entities.Aula;

public interface IAulaService extends IGenericService<Aula, AulaDTO> {
  public AulaDTO traerPorNombre(String nombre);
}
