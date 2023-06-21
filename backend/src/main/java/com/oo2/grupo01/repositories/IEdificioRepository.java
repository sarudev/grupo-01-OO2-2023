package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Edificio;

@Repository("edificioRepository")
public interface IEdificioRepository extends JpaRepository<Edificio, Long> {

	//implementar querys donde se traiga el edificio con sus aulas
	//y los sensores del edificio y cada aula
	@Query(value = "FROM Edificio e "
			+ "inner join fetch e.aulas a "
			+ "inner join fetch e.sensores s "
			+ "inner join fetch s.registros "
			+ "WHERE e.idLugar=?1 ")
	public Optional<Edificio> traerConDependencias(Long idLugar);
}
