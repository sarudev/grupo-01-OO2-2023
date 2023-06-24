package com.oo2.grupo01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.EspacioVerde;

@Repository("espacioVerdeRepository")
public interface IEspacioVerdeRepository extends JpaRepository<EspacioVerde, Long> {
  @Query("from EspacioVerde e where e.nombre=:nombre")
  public EspacioVerde findByName(@Param("nombre") String nombre);

}
