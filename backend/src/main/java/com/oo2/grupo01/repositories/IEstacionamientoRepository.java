package com.oo2.grupo01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Estacionamiento;

@Repository("estacionamientoRepository")
public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {
  @Query("from Estacionamiento e inner join fetch e.lugar p where e.nombre=:nombre and p.nombre=:lugar")
  public Estacionamiento findByName(@Param("nombre") String nombre, @Param("lugar") String lugar);

}
