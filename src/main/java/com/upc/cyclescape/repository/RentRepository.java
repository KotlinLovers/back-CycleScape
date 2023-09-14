package com.upc.cyclescape.repository;

import com.upc.cyclescape.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByBicycleId(Long bicycle_id);
}
