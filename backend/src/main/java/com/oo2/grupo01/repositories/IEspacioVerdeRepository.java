package com.oo2.grupo01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.EspacioVerde;

@Repository("espacioVerdeRepository")
public interface IEspacioVerdeRepository extends JpaRepository<EspacioVerde, Long> {

  @Query("FROM EspacioVerde ev "
      + "inner join fetch ev.sensores "
      + "inner join fetch ev.historial "
      + "WHERE ev.idLugar=?1")
  public Optional<EspacioVerde> traerConDependencias(Long idLugar);

}
