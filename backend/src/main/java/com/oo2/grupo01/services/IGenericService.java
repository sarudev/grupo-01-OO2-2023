package com.oo2.grupo01.services;

import java.util.List;

public interface IGenericService<T, TDTO> {
  public TDTO toDto(T lugar);

  public List<TDTO> toTdoList(List<T> lugares);

  public List<T> getAll();
}
