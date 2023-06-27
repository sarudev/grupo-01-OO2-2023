package com.oo2.grupo01.services;

import org.springframework.stereotype.Service;

import com.oo2.grupo01.entities.LoadedDB;
import com.oo2.grupo01.repositories.ILoadedDBRepository;

import lombok.RequiredArgsConstructor;

@Service("loadedDBService")
@RequiredArgsConstructor
public class LoadedDBService {
	private final ILoadedDBRepository repository;

	public void load() throws Exception {
		repository.save(new LoadedDB(true));
	}

	public boolean loaded() {
		return repository.findById(1l).orElse(new LoadedDB(false)).isLoaded();
	}
}
