package com.epam.lashchenkova.registrationServices.services;

import com.epam.lashchenkova.registrationServices.convertor.mappers.DoctorMapper;
import com.epam.lashchenkova.registrationServices.convertor.mappers.HospitalMapper;
import com.epam.lashchenkova.registrationServices.convertor.mappers.PatientMapper;
import com.epam.lashchenkova.registrationServices.dto.request.DoctorDto;
import com.epam.lashchenkova.registrationServices.dto.request.PatientDto;
import com.epam.lashchenkova.registrationServices.dto.response.DoctorResponseDto;
import com.epam.lashchenkova.registrationServices.entities.Doctor;
import com.epam.lashchenkova.registrationServices.repositories.DoctorRepository;
import com.epam.lashchenkova.registrationServices.repositories.HospitalRepository;
import com.epam.lashchenkova.registrationServices.repositories.PatientRepository;
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
