package com.oo2.grupo01.services;

import com.oo2.grupo01.dto.EspacioVerdeDTO;
import com.oo2.grupo01.entities.EspacioVerde;

public interface IEspacioVerdeService extends IGenericService<EspacioVerde, EspacioVerdeDTO> {
  public void add(String nombre);

  public EspacioVerde get(String nombre);
}
