package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Long> {

  @Query("FROM Aula a "
      + "inner join fetch a.lugar "
      + "inner join fetch a.sensores "
      + "inner join fetch a.historial "
      + "where a.idLugar=?1")
  public Optional<Aula> traerConDependencias(Long idLugar);
}
