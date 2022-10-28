package com.epam.lashchenkova.polyclinic.repositories;

import com.epam.lashchenkova.polyclinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

}
