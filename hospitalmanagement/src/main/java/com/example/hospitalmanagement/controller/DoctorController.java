package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.entity.Doctor;
import com.example.hospitalmanagement.exception.ResourceNotFoundException;
import com.example.hospitalmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.Collections;
import java.util.List;
import java.time.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

//*********************this added*************************************//


    //*********************this added*************************************//
    @PostMapping("/adddoctor")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor savedDoctor = doctorService.createDoctor(doctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
        } catch (Exception ex) {
            // Log the exception and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Doctor());
        }
    }

    @GetMapping("/{doctorid}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorid) {
        try {
            Doctor doctor = doctorService.getDoctorById(doctorid);
            return ResponseEntity.ok(doctor);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getalldoctor")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            return ResponseEntity.ok(doctors);
        } catch (Exception ex) {
            // Log the exception and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(new Doctor()));
        }
    }

    @PutMapping("/{doctorid}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long doctorid, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.updateDoctor(doctorid, doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{doctorid}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long doctorid) {
        doctorService.deleteDoctor(doctorid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getallbyname")
    public List<Doctor> getDoctorsByFirstNameAndLastNameAndSpecialization(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String specialization) {
        if (firstName != null && lastName != null && specialization != null) {
            return doctorService.findByNameOrSpecialization(firstName, lastName, specialization);
        } else {
            return doctorService.getAllDoctors();
        }
    }
}
