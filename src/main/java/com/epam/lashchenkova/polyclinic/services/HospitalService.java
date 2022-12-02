package com.epam.lashchenkova.polyclinic.services;


import com.epam.lashchenkova.polyclinic.convertor.mappers.HospitalMapper;
import com.epam.lashchenkova.polyclinic.dto.request.HospitalDto;
import com.epam.lashchenkova.polyclinic.dto.response.HospitalResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.repositories.HospitalRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HospitalService {

    private static final String NOT_FOUND = "Resource was not found";

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

    public HospitalResponseDto findById(Long id) throws NotFoundException {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        HospitalResponseDto hospitalResponseDto = mapper.entityToResponseDto(hospital.get());
        return hospitalResponseDto;
    }

    public HospitalResponseDto save(HospitalDto dto) {
        Hospital hospital = hospitalRepository.findByName(dto.getName());
        if (!Objects.isNull(hospital)) {
            throw new RuntimeException("Hospital with such name already exists");
        }
        hospital = hospitalRepository.save(mapper.dtoToEntity(dto));
        HospitalResponseDto hospitalResponseDto = mapper.entityToResponseDto(hospital);
        return hospitalResponseDto;
    }

    public void delete(Long id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        if (!hospital.isPresent()) {
            throw new RuntimeException("Hospital with such id not exists");
        }
        hospitalRepository.deleteById(id);
    }
}
