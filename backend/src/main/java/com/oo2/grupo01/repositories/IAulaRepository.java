package com.oo2.grupo01.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Long> {
	@Query("from Aula a inner join fetch a.lugar e where a.idLugar=:idAula and e.idLugar=:idEdificio")
	public Aula get(@Param("idEdificio") Long idEdificio, @Param("idAula") Long idAula);

	@Query("from Aula a inner join fetch a.lugar e where e.idLugar=:idEdificio")
	public List<Aula> getAllById(@Param("idEdificio") Long idEdificio);
}
