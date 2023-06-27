package com.oo2.grupo01.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Estacionamiento;

@Repository("estacionamientoRepository")
public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {
	@Query("from Estacionamiento e inner join fetch e.lugar p where e.idLugar=:idEstacionamiento and p.idLugar=:idParking")
	public Estacionamiento get(@Param("idParking") Long idParking, @Param("idEstacionamiento") Long idEstacionamiento);

	@Query("from Estacionamiento e inner join fetch e.lugar p where p.idLugar=:idEdificio")
	public List<Estacionamiento> getAllById(@Param("idEdificio") Long idEdificio);

}
