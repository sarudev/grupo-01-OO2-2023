package com.oo2.grupo01.services;

import com.oo2.grupo01.entities.Registro;

public interface IRegistroService {
	public void agregar(Registro registro);
	
	public void eliminar(Long id);
}
