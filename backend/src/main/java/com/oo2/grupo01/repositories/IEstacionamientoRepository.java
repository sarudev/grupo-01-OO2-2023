package com.oo2.grupo01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Estacionamiento;

@Repository("estacionamientoRepository")
public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {
  @Query("from Estacionamiento e inner join fetch Lugar l where e.idLugar=l.idLugar and l.nombre=:nombre")
  public Estacionamiento findByName(@Param("nombre") String nombre);

}
