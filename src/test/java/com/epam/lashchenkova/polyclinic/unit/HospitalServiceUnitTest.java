package com.epam.lashchenkova.polyclinic.unit;

import com.epam.lashchenkova.polyclinic.core.AbstractUnitTest;
import com.epam.lashchenkova.polyclinic.dto.request.HospitalDto;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.repositories.HospitalRepository;
import com.epam.lashchenkova.polyclinic.services.HospitalService;
import com.epam.lashchenkova.polyclinic.util.TestDataFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class HospitalServiceUnitTest extends AbstractUnitTest {

    @Autowired
    private HospitalService hospitalService;

    @MockBean
    private HospitalRepository hospitalRepository;

    @Test
    void whenAddHospitalWithExistingName_thenExceptionIsExpected() {
        Hospital testHospital = TestDataFactory.getStubHospitalOne();
        HospitalDto testHospitalDto = TestDataFactory.getStubHospitalOneDto();

        Mockito
                .when(hospitalRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(testHospital);

        Assertions.assertThrows(RuntimeException.class,
                () -> hospitalService.save(testHospitalDto));
    }
}
