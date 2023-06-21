package com.oo2.grupo01.services;

import java.util.List;

import com.oo2.grupo01.dto.EdificioDTO;
import com.oo2.grupo01.entities.Edificio;

public interface IEdificioService extends IGenericService<Edificio, EdificioDTO> {
	public List<EdificioDTO> traerTodos();
}
