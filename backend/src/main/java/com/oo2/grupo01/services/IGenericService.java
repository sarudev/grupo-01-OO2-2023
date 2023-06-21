package com.oo2.grupo01.services;

public interface IGenericService<T, TDTO> {
  
  public T traerConDependencias(Long id);
  
  public T traer(Long id);

  public void eliminar(Long id);
}
