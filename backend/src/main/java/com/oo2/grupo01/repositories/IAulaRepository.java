package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Long> {

	@Query(value = "SELECT * FROM Aula a INNER JOIN Edificio e WHERE e.idEdificio=?2 AND a.nombre=?1", nativeQuery = true)
	public Optional<Aula> traerPorNombre(String nombre, long idEdificio);
	
	
	//implementar query donde se trae el aula junto con sus sensores
	public Optional<Aula> findByNombreAndEdificio_IdLugar(String nombre, Long idEdificio);
}
