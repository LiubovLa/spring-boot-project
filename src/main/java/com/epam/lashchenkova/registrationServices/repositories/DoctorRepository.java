package com.epam.lashchenkova.registrationServices.repositories;

import com.epam.lashchenkova.registrationServices.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
