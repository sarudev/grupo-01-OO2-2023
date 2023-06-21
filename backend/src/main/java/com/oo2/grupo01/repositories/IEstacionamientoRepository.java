package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Estacionamiento;

@Repository("estacionamientoRepository")
public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {

  // implementar query que traiga el estacionamiento con sus sensores
  @Query("FROM Estacionamiento e "
      + "INNER JOIN fetch e.lugar "
      + "INNER JOIN FETCH e.sensores "
      + "INNER JOIN FETCH e.historial "
      + "WHERE e.idLugar=?1")
  public Optional<Estacionamiento> traerConDependencias(Long idLugar);

}
