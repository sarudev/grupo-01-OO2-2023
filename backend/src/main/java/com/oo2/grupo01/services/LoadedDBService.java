package com.oo2.grupo01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.LoadedDB;
import com.oo2.grupo01.repositories.ILoadedDBRepository;

import lombok.AllArgsConstructor;

@Service("loadedDBService")
@AllArgsConstructor
public class LoadedDBService {
  @Autowired
  ILoadedDBRepository repository;

  public void load() throws Exception {
    repository.save(new LoadedDB(true));
  }

  public boolean loaded() {
    return repository.findById(1l).orElse(new LoadedDB(false)).isLoaded();
  }
}
