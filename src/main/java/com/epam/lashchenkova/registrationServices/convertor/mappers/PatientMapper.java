package com.epam.lashchenkova.registrationServices.convertor.mappers;

import com.epam.lashchenkova.registrationServices.dto.request.PatientDto;
import com.epam.lashchenkova.registrationServices.dto.response.PatientResponseDto;
import com.epam.lashchenkova.registrationServices.entities.Patient;
import com.epam.lashchenkova.registrationServices.repositories.DoctorRepository;
import com.epam.lashchenkova.registrationServices.repositories.HospitalRepository;
import com.epam.lashchenkova.registrationServices.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

@Component
public class PatientMapper {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    HospitalMapper mapper;

    public PatientResponseDto entityToResponseDto(Patient patient) {
        return new PatientResponseDto()
                .setId(patient.getId())
                .setFirstName(patient.getFirstName())
                .setLastName(patient.getLastName())
                .setAddress(patient.getAddress())
                .setBirthday(patient.getBirthday())
                .setHospital(patient.getDoctor().getHospital().getName())
                .setDoctor(StringUtils.join(patient.getDoctor().getFirstName(), " ",
                        patient.getDoctor().getLastName()));
    }

    public Patient dtoToEntity(PatientDto dto) {
        return new Patient()
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setAddress(dto.getAddress())
                .setBirthday(dto.getBirthday())
                .setDoctor(doctorRepository.findById(dto.getDoctor()).get());
    }

    public PatientDto entityToDto(Patient patient) {
        return new PatientDto()
                .setId(patient.getId())
                .setFirstName(patient.getFirstName())
                .setLastName(patient.getLastName())
                .setAddress(patient.getAddress())
                .setBirthday(patient.getBirthday())
                .setDoctor(patient.getDoctor().getId());
    }

    public PatientResponseDto dtoToResponseDto(PatientDto patientDto) {
        return new PatientResponseDto()
                .setId(patientDto.getId())
                .setFirstName(patientDto.getFirstName())
                .setLastName(patientDto.getLastName())
                .setBirthday(patientDto.getBirthday())
                .setAddress(patientDto.getAddress())
                .setDoctor(StringUtils.join(doctorRepository.findById(patientDto.getDoctor()).get().getFirstName(), " ",
                        doctorRepository.findById(patientDto.getDoctor()).get().getLastName()))
                .setHospital(doctorRepository.findById(patientDto.getDoctor()).get().getHospital().getName());
    }
}
