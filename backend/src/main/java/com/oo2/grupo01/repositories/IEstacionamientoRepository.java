package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Estacionamiento;

@Repository("estacionamientoRepository")
public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {
	
	//implementar query que traiga el estacionamiento con sus sensores
	@Query("FROM Estacionamiento e "
			+ "INNER JOIN fetch e.parking "
			+ "WHERE e.numero=?1")
	public Optional<Estacionamiento> traerPorNumero(int numero);
	
	
	public Optional<Estacionamiento> findByNumeroAndParking_IdLugar(int numero, Long idParking);
	
	
	
	
	
}
