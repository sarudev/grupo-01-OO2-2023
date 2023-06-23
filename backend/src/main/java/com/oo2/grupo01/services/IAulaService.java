package com.oo2.grupo01.services;

import com.oo2.grupo01.dto.AulaDTO;
import com.oo2.grupo01.entities.Aula;
import com.oo2.grupo01.entities.Edificio;

public interface IAulaService extends IGenericService<Aula, AulaDTO> {
  public void add(Edificio edificio, String nombre);

  public Aula get(String edificio, String nombre) throws Exception;
}
