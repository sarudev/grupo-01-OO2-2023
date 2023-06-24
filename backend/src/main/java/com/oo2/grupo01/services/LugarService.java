package com.oo2.grupo01.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.Lugar;
import com.oo2.grupo01.entities.Sensor;
import com.oo2.grupo01.repositories.ILugarRepository;

@Service("lugarService")
public class LugarService {
	
	@Autowired
	ILugarRepository lugarRepository;
	
	public List<Sensor> findSensoresByLugarId(Long lugarId){
		Optional<Lugar> lugarOptional = lugarRepository.findById(lugarId);
		if(lugarOptional.isPresent()) {
			Lugar lugar = lugarOptional.get();
			return lugar.getSensores();
		}
		return Collections.emptyList();
	}
	
	
}
