package com.epam.lashchenkova.polyclinic.services;

import com.epam.lashchenkova.polyclinic.convertor.mappers.DoctorMapper;
import com.epam.lashchenkova.polyclinic.convertor.mappers.PatientMapper;
import com.epam.lashchenkova.polyclinic.dto.request.PatientDto;
import com.epam.lashchenkova.polyclinic.dto.response.PatientResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Patient;
import com.epam.lashchenkova.polyclinic.repositories.DoctorRepository;
import com.epam.lashchenkova.polyclinic.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PatientMapper patientMapper;

    @Autowired
    private final DoctorMapper doctorMapper;

    public List<PatientResponseDto> getAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(
                        patientMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public PatientResponseDto save(PatientDto patientDto) {
        patientRepository.save(patientMapper.dtoToEntity(patientDto));
        return patientMapper.dtoToResponseDto(patientDto);
    }

    public PatientResponseDto addDoctor(Long doctorId, Long patientId) {
        PatientDto patientDto = patientMapper.entityToDto(patientRepository.findById(patientId).get());
        patientDto.setDoctor(doctorId);
        return patientMapper.entityToResponseDto(patientRepository.save(patientMapper.dtoToEntity(patientDto)));
    }

   public PatientResponseDto findById(Long id) {
        return patientMapper.entityToResponseDto(patientRepository.findById(id).get());
    }


    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
