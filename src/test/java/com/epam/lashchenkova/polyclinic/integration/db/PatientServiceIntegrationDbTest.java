package com.epam.lashchenkova.polyclinic.integration.db;

import com.epam.lashchenkova.polyclinic.core.AbstractDbTest;
import com.epam.lashchenkova.polyclinic.dto.request.PatientDto;
import com.epam.lashchenkova.polyclinic.dto.response.PatientResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.entities.Patient;
import com.epam.lashchenkova.polyclinic.repositories.PatientRepository;
import com.epam.lashchenkova.polyclinic.services.PatientService;
import com.epam.lashchenkova.polyclinic.util.Builder;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public class PatientServiceIntegrationDbTest extends AbstractDbTest {

    private static final long EXPECTED_DOCTOR_ID = 2L;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void whenPatientUpdateDoctor_thenDoctorIdChangedInDb() throws NotFoundException {
        Patient testPatientDto = patientRepository.findById(EXPECTED_DOCTOR_ID).get();
        Assertions.assertEquals(3L, testPatientDto.getDoctor().getId());

        patientService.addDoctor(2L, EXPECTED_DOCTOR_ID);

        Patient actualPatient = patientRepository.findById(EXPECTED_DOCTOR_ID).get();
        Assertions.assertAll(
                () ->
                {
                    Assertions.assertTrue(actualPatient.getId() != null);
                    Assertions.assertEquals(testPatientDto.getAddress(), actualPatient.getAddress());
                    Assertions.assertEquals(testPatientDto.getFirstName(), actualPatient.getFirstName());
                    Assertions.assertEquals(testPatientDto.getLastName(), actualPatient.getLastName());
                    Assertions.assertEquals(testPatientDto.getId(), actualPatient.getId());
                    Assertions.assertNotEquals(actualPatient.getDoctor().getId(), testPatientDto.getDoctor().getId());
                    Assertions.assertEquals(EXPECTED_DOCTOR_ID, actualPatient.getDoctor().getId());
                    Assertions.assertEquals(testPatientDto.getBirthday(), actualPatient.getBirthday());
                }
        );
    }

    @Test
    void whenPatientIsDeleted_thenPatientIsDeletedInDb() {
        Optional<Patient> presentPatient = patientRepository.findById(2L);
        Assertions.assertEquals(2L, (long) presentPatient.get().getId());

        patientService.delete(2L);

        Optional<Patient> actualPatient = patientRepository.findById(presentPatient.get().getId());
        Assertions.assertFalse(actualPatient.isPresent());
    }
}