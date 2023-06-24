package com.oo2.grupo01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Edificio;

@Repository("edificioRepository")
public interface IEdificioRepository extends JpaRepository<Edificio, Long> {
  // @Query(value = "select * from edificio e inner join lugar le inner join aula
  // a inner join lugar la where e.id_lugar=le.id_lugar and a.id_lugar=la.id_lugar
  // and a.id_edificio=e.id_lugar and le.nombre=:nombre", nativeQuery = true)
  // @Query(value = "select * from edificio e inner join lugar le inner join aula
  // a inner join lugar la where e.id_lugar=le.id_lugar and a.id_lugar=la.id_lugar
  // and le.nombre=:lugar and la.nombre=:nombre", nativeQuery = true)
  @Query("from Edificio e left join fetch e.aulas a where e.nombre=:nombre")
  public Edificio findByName(@Param("nombre") String nombre);
}
