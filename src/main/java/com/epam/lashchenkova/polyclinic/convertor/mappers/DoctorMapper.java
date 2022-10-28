package com.epam.lashchenkova.polyclinic.convertor.mappers;

import com.epam.lashchenkova.polyclinic.dto.request.DoctorDto;
import com.epam.lashchenkova.polyclinic.dto.response.DoctorResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Doctor;
import com.epam.lashchenkova.polyclinic.repositories.HospitalRepository;
import com.epam.lashchenkova.polyclinic.repositories.PatientRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PatientRepository patientRepository;

    public DoctorResponseDto entityToResponseDto(Doctor doctor) {
        return new DoctorResponseDto()
                .setId(doctor.getId())
                .setFirstName(doctor.getFirstName())
                .setLastName(doctor.getLastName())
                .setSpecialization(doctor.getSpecialization())
                .setHospital(doctor.getHospital().getName())
                .setPatients(doctor.getPatients().stream().map(p -> StringUtils.join(p.getFirstName(), " ", p.getLastName())).collect(Collectors.toList()));
    }

    public Doctor dtoToEntity(DoctorDto dto) {
        return new Doctor()
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setSpecialization(dto.getSpecialization())
                .setHospital(hospitalRepository.findById(dto.getHospital()).get())
                .setPatients(Optional.ofNullable(dto.getPatients()).orElse(new ArrayList<>()).stream().map(p -> patientRepository.findById(p).get()).collect(Collectors.toList()));
    }

    public DoctorDto entityToDto(Doctor doctor) {
        return new DoctorDto().setId(doctor.getId())
                .setFirstName(doctor.getFirstName())
                .setLastName(doctor.getLastName())
                .setSpecialization(doctor.getSpecialization())
                .setHospital(doctor.getHospital().getId())
                .setPatients(doctor.getPatients().stream().map(p -> p.getId()).collect(Collectors.toList()));
    }

    public DoctorResponseDto dtoToResponseDto(DoctorDto doctorDto) {
        return new DoctorResponseDto()
                .setId(doctorDto.getId())
                .setFirstName(doctorDto.getFirstName())
                .setLastName(doctorDto.getLastName())
                .setSpecialization(doctorDto.getSpecialization())
                .setHospital(hospitalRepository.findById(doctorDto.getHospital()).get().getName())
                .setPatients(doctorDto.getPatients().stream().map(p -> StringUtils.join(patientRepository.findById(p).get().getFirstName(), " ",
                        patientRepository.findById(p).get().getLastName())).collect(Collectors.toList()));
    }
}
