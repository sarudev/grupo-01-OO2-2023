package com.oo2.grupo01.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Long> {
  @Query("from Aula a inner join fetch a.lugar e where a.nombre=:nombre and e.nombre=:lugar")
  public Aula findByName(@Param("nombre") String nombre, @Param("lugar") String lugar);

  @Query("from Aula a inner join fetch a.lugar e where e.nombre=:lugar")
  public List<Aula> findAll(@Param("lugar") String lugar);
}
