package com.epam.lashchenkova.registrationServices.repositories;

import com.epam.lashchenkova.registrationServices.entities.Doctor;
import com.epam.lashchenkova.registrationServices.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

}
