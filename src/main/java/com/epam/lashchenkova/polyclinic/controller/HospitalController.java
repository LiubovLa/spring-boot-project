package com.epam.lashchenkova.polyclinic.controller;

import com.epam.lashchenkova.polyclinic.convertor.mappers.HospitalMapper;
import com.epam.lashchenkova.polyclinic.dto.request.HospitalDto;
import com.epam.lashchenkova.polyclinic.dto.response.HospitalResponseDto;
import com.epam.lashchenkova.polyclinic.entities.Hospital;
import com.epam.lashchenkova.polyclinic.entities.Patient;
import com.epam.lashchenkova.polyclinic.services.HospitalService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/hospital")
public class HospitalController {

    private static final String NOT_FOUND = "Resource was not found";
    private static final String INVALID_INPUT = "Invalid input parameters";
    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/all")
    public ResponseEntity<String> getAll() {
        final List<HospitalResponseDto> hospitals = hospitalService.getAll();
        return hospitals != null &&  !hospitals.isEmpty()
                ? new ResponseEntity<>("vblk", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<HospitalResponseDto> save(@Valid @RequestBody HospitalDto hospitalDto) {
        if (Objects.isNull(hospitalDto)) {
            throw new InvalidParameterException(INVALID_INPUT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.save(hospitalDto));
    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<HospitalResponseDto> save(@PathVariable(value = "hospitalId") Long hospitalId) throws Exception {
        if (Objects.isNull(hospitalService.findById(hospitalId))) {
            throw new NotFoundException(NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(hospitalService.findById(hospitalId));
    }

    @DeleteMapping("/delete={hospitalId}")
    public ResponseEntity<Hospital> deleteHospital(@PathVariable(value = "hospitalId") Long hospitalId) {
        hospitalService.delete(hospitalId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
