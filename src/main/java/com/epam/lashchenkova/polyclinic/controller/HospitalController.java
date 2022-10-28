package com.epam.lashchenkova.polyclinic.controller;

import com.epam.lashchenkova.polyclinic.dto.request.HospitalDto;
import com.epam.lashchenkova.polyclinic.dto.response.HospitalResponseDto;
import com.epam.lashchenkova.polyclinic.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/all")
    public ResponseEntity<List<HospitalResponseDto>> getAll() {
        final List<HospitalResponseDto> hospitals = hospitalService.getAll();
        return hospitals != null &&  !hospitals.isEmpty()
                ? new ResponseEntity<>(hospitals, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<HospitalResponseDto> save(@RequestBody HospitalDto hospitalDto) {
        return hospitalDto != null
                ? new ResponseEntity<>(hospitalService.save(hospitalDto), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
