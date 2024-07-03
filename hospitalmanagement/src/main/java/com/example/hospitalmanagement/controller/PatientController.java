package com.example.hospitalmanagement.controller;

import ch.qos.logback.classic.Logger;
import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.exception.ResourceNotFoundException;
import com.example.hospitalmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/addpatient")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        try {
            Patient savedPatient = patientService.createPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Patient());
        }
    }

    @GetMapping("/{patientsid}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long patientsid) {
        try {
            Patient patient = patientService.getPatientById(patientsid);
            return ResponseEntity.ok(patient);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Patient>> getAllPatients() {
        try {
            List<Patient> patients = patientService.getAllPatients();
            return ResponseEntity.ok(patients);
        } catch (Exception ex) {
            // Log the exception and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(new Patient()));
        }
    }
//    @GetMapping("/patients")
//    public ResponseEntity<List<Patient>> getAllPatients() {
//        List<Patient> patients = patientService.getAllPatients();
//        return ResponseEntity.ok(patients);
//    }

    @PutMapping("/{patientsid}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long patientsid, @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(patientsid, patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{patientsid}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long patientsid) {
        patientService.deletePatient(patientsid);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getallbyname")
    public ResponseEntity<List<Patient>> getAllPatientsByFirstNameAndLastName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        List<Patient> patients = patientService.findByFirstNameAndLastName(firstName, lastName);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
