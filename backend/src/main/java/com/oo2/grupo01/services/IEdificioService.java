package com.oo2.grupo01.services;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;

public interface IEdificioService extends IGenericService<Edificio, EdificioDTO> {
  public void add(String nombre);

  public Edificio get(String nombre);
}
