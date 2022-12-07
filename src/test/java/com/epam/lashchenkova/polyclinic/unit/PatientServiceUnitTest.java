package com.epam.lashchenkova.polyclinic.unit;

import com.epam.lashchenkova.polyclinic.core.AbstractUnitTest;
import com.epam.lashchenkova.polyclinic.dto.response.HospitalResponseDto;
import com.epam.lashchenkova.polyclinic.dto.response.PatientResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.entities.Patient;
import com.epam.lashchenkova.polyclinic.repositories.HospitalRepository;
import com.epam.lashchenkova.polyclinic.repositories.PatientRepository;
import com.epam.lashchenkova.polyclinic.services.HospitalService;
import com.epam.lashchenkova.polyclinic.services.PatientService;
import com.epam.lashchenkova.polyclinic.util.TestDataFactory;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

public class PatientServiceUnitTest extends AbstractUnitTest {

    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    void whenGetPatientByExistingId_thenPatientResponseDtoIsReturned() {
        Patient testPatient = TestDataFactory.getStubPatientOne();

        Mockito
                .when(patientRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.ofNullable(testPatient));
        PatientResponseDto patientResponseDto = patientService.findById(ENTITY_STUB_ID);

        Assertions.assertAll(
                () -> {
                    Assertions.assertEquals(patientResponseDto.getFirstName(), testPatient.getFirstName());
                    Assertions.assertEquals(patientResponseDto.getLastName(), testPatient.getLastName());
                    Assertions.assertEquals(patientResponseDto.getAddress(), testPatient.getAddress());
                    Assertions.assertEquals(patientResponseDto.getBirthday(), testPatient.getBirthday());
                    Assertions.assertEquals(patientResponseDto.getDoctor(), testPatient.getDoctor().getFirstName() + " " +
                            testPatient.getDoctor().getLastName());
                    Assertions.assertEquals(patientResponseDto.getHospital(), testPatient.getDoctor().getHospital().getName());
                }
        );
    }
}
