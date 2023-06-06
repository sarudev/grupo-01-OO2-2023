package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Parking;

@Repository("parkingRepository")
public interface IParkingRepository extends JpaRepository<Parking, Long> {
	
	//implementar query que traiga el parking con los estacionamientos
	// y los sensores del parking y de cada estacionamiento
	@Query("FROM Parking p "
			+ "inner join fetch p.estacionamientos e "
			+ "inner join fetch p.sensores s "
			+ "inner join fetch e.sensores "
			+ "inner join fetch s.registros "
			+ "WHERE p.idLugar=?1")
	public Optional<Parking> traerConDependencias(Long idLugar);
	
}
