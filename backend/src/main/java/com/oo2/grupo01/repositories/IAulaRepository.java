package com.oo2.grupo01.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Aula;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula, Serializable> {

}
