package com.oo2.grupo01.services;

public interface IGenericService<T, TDTO> {
  
  public TDTO traerConDependencias(Long id);

  public void eliminar(Long id);
}
