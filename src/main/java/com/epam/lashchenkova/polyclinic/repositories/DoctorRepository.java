package com.epam.lashchenkova.polyclinic.repositories;

import com.epam.lashchenkova.polyclinic.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
