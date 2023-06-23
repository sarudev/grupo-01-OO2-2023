package com.oo2.grupo01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Long> {
  // @Query(value = "from Aula a " +
  // "inner join fetch a.lugar e " +
  // "where e.nombre=:lugar " +
  // "and a.nombre=:nombre ")
  // @Query(value = "SELECT * FROM aula a inner join lugar l where
  // a.id_lugar=l.id_lugar and l.nombre=:nombre or l.nombre=:lugar", nativeQuery =
  // true)
  @Query("from Aula a inner join fetch a.lugar l where a.nombre=:nombre and l.nombre=:lugar")
  public Aula findByName(@Param("nombre") String nombre, @Param("lugar") String lugar);
}
