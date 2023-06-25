package com.oo2.grupo01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Historial;

@Repository("historialRepository")
public interface IHistorialRepository extends JpaRepository<Historial, Long> {
}
