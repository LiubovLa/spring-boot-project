package com.epam.lashchenkova.polyclinic.services;

import com.epam.lashchenkova.polyclinic.convertor.mappers.DoctorMapper;
import com.epam.lashchenkova.polyclinic.dto.request.DoctorDto;
import com.epam.lashchenkova.polyclinic.dto.response.DoctorResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Doctor;
import com.epam.lashchenkova.polyclinic.repositories.DoctorRepository;
import com.epam.lashchenkova.polyclinic.repositories.HospitalRepository;
import com.epam.lashchenkova.polyclinic.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DoctorService {

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final HospitalRepository hospitalRepository;

    @Autowired
    private final DoctorMapper mapper;

    public List<DoctorResponseDto> getAll() {
        List<Doctor> hospitals = doctorRepository.findAll();
        return hospitals.stream().map(
                        mapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public DoctorResponseDto findById(Long id) {
        return mapper.entityToResponseDto(doctorRepository.findById(id).get());
    }

    public DoctorResponseDto save(DoctorDto doctorDto) {
        return mapper.entityToResponseDto(doctorRepository.save(mapper.dtoToEntity(doctorDto)));
    }

    public void delete(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }
}
