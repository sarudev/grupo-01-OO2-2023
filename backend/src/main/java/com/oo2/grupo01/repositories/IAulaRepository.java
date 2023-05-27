package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Long> {

	@Query("FROM Aula a inner join fetch a.edificio inner join fetch a.sensores where a.nombre=?1")
	public Optional<Aula> traerPorNombre(String nombre);
	
	
	//implementar query donde se trae el aula junto con sus sensores
	public Optional<Aula> findByNombreAndEdificio_IdLugar(String nombre, Long idEdificio);
}
