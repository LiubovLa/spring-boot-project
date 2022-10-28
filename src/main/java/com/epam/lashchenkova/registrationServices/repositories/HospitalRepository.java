package com.epam.lashchenkova.registrationServices.repositories;

import com.epam.lashchenkova.registrationServices.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}
