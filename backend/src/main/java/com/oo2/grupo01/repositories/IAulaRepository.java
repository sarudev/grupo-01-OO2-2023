package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Long> {

	//implementar query donde se trae el aula junto con sus sensores
	public Optional<Aula> traerPorNombre(String nombre, Long idEdificio);
}
