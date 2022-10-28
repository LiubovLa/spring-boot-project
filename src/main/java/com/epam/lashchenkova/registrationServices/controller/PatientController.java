package com.epam.lashchenkova.registrationServices.controller;

import com.epam.lashchenkova.registrationServices.dto.request.PatientDto;
import com.epam.lashchenkova.registrationServices.dto.response.PatientResponseDto;
import com.epam.lashchenkova.registrationServices.entities.Patient;
import com.epam.lashchenkova.registrationServices.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private final PatientService patientService;

    @GetMapping("/all")
    public ResponseEntity<List<PatientResponseDto>> getAll() {
        final List<PatientResponseDto> patients = patientService.getAll();
        return patients != null &&  !patients.isEmpty()
                ? new ResponseEntity<>(patients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<PatientResponseDto> save(@RequestBody PatientDto patientDto) {
        return patientDto != null
                ? new ResponseEntity<>(patientService.save(patientDto), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/doctorId={doctorId}&patientId={patientId}")
    public ResponseEntity<PatientResponseDto> updateDoctor(@PathVariable(value = "doctorId") Long doctorId,
                                                           @PathVariable(value = "patientId") Long patientId) {
        PatientResponseDto patientResponseDto = patientService.addDoctor(doctorId, patientId);
        return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete={patientId}")
    public ResponseEntity<Patient> deletePatient(@PathVariable(value = "patientId") Long patientId) {
        patientService.delete(patientId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
