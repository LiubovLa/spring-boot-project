package com.epam.lashchenkova.registrationServices.controller;

//import com.epam.lashchenkova.registrationServices.dto.DoctorDto;
import com.epam.lashchenkova.registrationServices.dto.request.DoctorDto;
import com.epam.lashchenkova.registrationServices.dto.request.PatientDto;
import com.epam.lashchenkova.registrationServices.dto.response.DoctorResponseDto;
import com.epam.lashchenkova.registrationServices.dto.response.PatientResponseDto;
import com.epam.lashchenkova.registrationServices.entities.Doctor;
import com.epam.lashchenkova.registrationServices.entities.Patient;
import com.epam.lashchenkova.registrationServices.services.DoctorService;
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
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/all")
    public ResponseEntity<List<DoctorResponseDto>> getAll() {
        final List<DoctorResponseDto> doctors = doctorService.getAll();
        return doctors != null &&  !doctors.isEmpty()
                ? new ResponseEntity<>(doctors, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<DoctorResponseDto> save(@RequestBody DoctorDto doctorDto) {
        return doctorDto != null
                ? new ResponseEntity<>(doctorService.save(doctorDto), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping("/delete={doctorId}")
//    public ResponseEntity<Doctor> deletePatient(@PathVariable(value = "doctorId") Long doctorId) {
//        doctorService.delete(doctorId);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
