package com.epam.lashchenkova.polyclinic.util;

import com.epam.lashchenkova.polyclinic.dto.request.HospitalDto;
import com.epam.lashchenkova.polyclinic.dto.request.PatientDto;
import com.epam.lashchenkova.polyclinic.dto.response.HospitalResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Doctor;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.entities.Patient;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TestDataFactory {

    public static Hospital getStubHospitalOne() {
        return Builder.build(Hospital.class)
                .with(s -> s.setId(1L))
                .with(s -> s.setName("Hospital"))
                .with(s -> s.setAddress("Test street 23"))
                .get();
    }

    public static HospitalDto getStubHospitalOneDto() {
        return Builder.build(HospitalDto.class)
                .with(s -> s.setName("Hospital"))
                .with(s -> s.setAddress("Test street 23"))
                .get();
    }

    public static Patient getStubPatientOne() {
        return Builder.build(Patient.class)
                .with(s -> s.setId(1L))
                .with(s -> s.setFirstName("Miron"))
                .with(s -> s.setLastName("Budavi"))
                .with(s -> s.setAddress("Test street 24"))
                .with(s -> s.setBirthday(new Date()))
                .with(s -> s.setDoctor(getStubDoctor()))
                .get();
    }

    public static PatientDto getStubPatientOneDto() {
        return Builder.build(PatientDto.class)
                .with(s -> s.setFirstName("Miron"))
                .with(s -> s.setLastName("Budavi"))
                .with(s -> s.setAddress("Test street 24"))
                .with(s -> s.setBirthday(new Date()))
                .with(s -> s.setDoctor(1L))
                .get();
    }

    public static HospitalResponseDto getStubHospitalOneResponseDto() {
        return Builder.build(HospitalResponseDto.class)
                .with(s -> s.setName("Hospital"))
                .with(s -> s.setAddress("Test street 23"))
                .get();
    }

    public static Doctor getStubDoctor() {
        return Builder.build(Doctor.class)
                .with(c -> c.setId(1L))
                .with(c -> c.setFirstName("Test"))
                .with(c -> c.setLastName("Testing"))
                .with(c -> c.setSpecialization("therapist"))
                .with(c -> c.setHospital(getStubHospital()))
                .get();
    }

    public static Hospital getStubHospital() {
        return Builder.build(Hospital.class)
                .with(c -> c.setId(1L))
                .with(c -> c.setName("Test"))
                .with(c -> c.setAddress("Test street 25"))
                .get();
    }
}
