package com.epam.lashchenkova.polyclinic.convertor.mappers;

import com.epam.lashchenkova.polyclinic.dto.request.HospitalDto;
import com.epam.lashchenkova.polyclinic.dto.response.HospitalResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.repositories.DoctorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HospitalMapper {

    @Autowired
    DoctorRepository doctorRepository;

    public HospitalResponseDto entityToResponseDto(Hospital hospital){
        return new HospitalResponseDto()
                .setId(hospital.getId())
                .setAddress(hospital.getAddress())
                .setName(hospital.getName())
                .setDoctors(Optional.ofNullable(hospital.getDoctors()).orElse(new ArrayList<>()).stream().map(d -> StringUtils.join(d.getFirstName(), " ", d.getLastName())).collect(Collectors.toList()));
    }

    public Hospital dtoToEntity(HospitalDto dto) {
        return new Hospital()
                .setName(dto.getName())
                .setAddress(dto.getAddress());
    }
}
