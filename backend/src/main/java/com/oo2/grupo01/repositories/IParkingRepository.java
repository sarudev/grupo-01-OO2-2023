package com.oo2.grupo01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oo2.grupo01.entities.Parking;

public interface IParkingRepository extends JpaRepository<Parking, Long> {

}
