package com.oo2.grupo01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo01.entities.Parking;

@Repository("parkingRepository")
public interface IParkingRepository extends JpaRepository<Parking, Long> {

}
