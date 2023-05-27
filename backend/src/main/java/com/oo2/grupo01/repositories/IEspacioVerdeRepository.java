package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.EspacioVerde;

@Repository("espacioVerdeRepository")
public interface IEspacioVerdeRepository extends JpaRepository<EspacioVerde, Long> {
	
	//implementar query que traiga los espacios verdes con sus sensores
	@Query("FROM EspacioVerde ev "
			+ "inner join fetch ev.sensores "
			+ "WHERE ev.ubicacion=?1")
	public Optional<EspacioVerde> traerPorUbicacion(String ubicacion);
	
	
	public Optional<EspacioVerde> findByUbicacion(String ubicacion);
	
	
}
