package com.oo2.grupo01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.LoadedDB;

@Repository("loadedDBRepository")
public interface ILoadedDBRepository extends JpaRepository<LoadedDB, Long> {
}
