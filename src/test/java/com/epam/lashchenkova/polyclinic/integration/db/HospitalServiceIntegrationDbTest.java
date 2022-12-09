package com.epam.lashchenkova.polyclinic.integration.db;

import com.epam.lashchenkova.polyclinic.core.AbstractDbTest;
import com.epam.lashchenkova.polyclinic.dto.request.HospitalDto;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.repositories.HospitalRepository;
import com.epam.lashchenkova.polyclinic.services.HospitalService;
import com.epam.lashchenkova.polyclinic.util.Builder;
import com.epam.lashchenkova.polyclinic.util.TestDataFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class HospitalServiceIntegrationDbTest extends AbstractDbTest {

    private static final String EXISTING_NAME = "Healthy baby";
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    void whenValidHospitalIsProvided_thenHospitalIsCreatedInDb() {
        HospitalDto testHospitalDto = TestDataFactory.getStubHospitalOneDto();
        hospitalService.save(testHospitalDto);

        Hospital actualPatient = hospitalRepository.findByName(testHospitalDto.getName());
        Assertions.assertAll(
                () ->
                {
                    Assertions.assertTrue(actualPatient.getId() != null);
                    Assertions.assertEquals(testHospitalDto.getName(), actualPatient.getName());
                    Assertions.assertEquals(testHospitalDto.getAddress(), actualPatient.getAddress());
                }
        );
    }

    @Test
    void whenHospitalWithExistingNameIsProvided_thenExceptionIsExpectedAndHospitalIsNotCreatedInDb() {
        HospitalDto testHospitalDto =
                Builder.build(HospitalDto.class)
                        .with(s -> s.setName(EXISTING_NAME))
                        .with(s -> s.setAddress("Test street 23"))
                        .get();
        Assertions.assertThrows(RuntimeException.class,
                () -> hospitalService.save(testHospitalDto));
        Hospital existingHospital = hospitalRepository.findByName(EXISTING_NAME);
        Assertions.assertNotEquals(testHospitalDto.getAddress(), existingHospital.getAddress());
    }

    @Test
    void whenHospitalIsDeleted_thenHospitalIsDeletedInDb() {
        Optional<Hospital> presentHospital = hospitalRepository.findById(2L);
        Assertions.assertEquals(2L, (long) presentHospital.get().getId());

        hospitalService.delete(2L);

        Optional<Hospital> actualPatient = hospitalRepository.findById(presentHospital.get().getId());
        Assertions.assertFalse(actualPatient.isPresent());
    }
}