package com.epam.lashchenkova.polyclinic.services;


import com.epam.lashchenkova.polyclinic.convertor.mappers.HospitalMapper;
import com.epam.lashchenkova.polyclinic.dto.request.HospitalDto;
import com.epam.lashchenkova.polyclinic.dto.response.HospitalResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HospitalService {

    @Autowired
    private final HospitalRepository hospitalRepository;

    @Autowired
    private final HospitalMapper mapper;

    public List<HospitalResponseDto> getAll() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals.stream().map(
                        mapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public HospitalResponseDto findById(Long id) {
        return mapper.entityToResponseDto(hospitalRepository.findById(id).get());
    }

    public HospitalResponseDto save(HospitalDto dto) {
        return mapper.entityToResponseDto(hospitalRepository.save(mapper.dtoToEntity(dto)));
    }
}
