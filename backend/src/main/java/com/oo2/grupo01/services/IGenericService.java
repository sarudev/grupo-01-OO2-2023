package com.oo2.grupo01.services;

public interface IGenericService<T, TDTO> {
  public void agregar(T object);

  public TDTO traer(Long id);

  public void eliminar(Long id);
}
