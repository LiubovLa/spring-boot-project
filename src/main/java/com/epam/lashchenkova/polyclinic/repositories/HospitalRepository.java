package com.epam.lashchenkova.polyclinic.repositories;

import com.epam.lashchenkova.polyclinic.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    Hospital findByName(String hospitalName);
}
