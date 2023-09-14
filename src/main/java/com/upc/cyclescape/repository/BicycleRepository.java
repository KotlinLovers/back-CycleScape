package com.upc.cyclescape.repository;

import com.upc.cyclescape.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
}
