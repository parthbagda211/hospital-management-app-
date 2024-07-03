package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.entity.Patient;
import com.example.hospitalmanagement.exception.ResourceNotFoundException;
import com.example.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import  java.lang.*;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
    }

    public Patient updatePatient(Long patientId, Patient updatedPatient) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));

        patient.setFirstName(updatedPatient.getFirstName());
        patient.setLastName(updatedPatient.getLastName());
        patient.setDateOfBirth(updatedPatient.getDateOfBirth());
        patient.setGender(updatedPatient.getGender());
        patient.setAddress(updatedPatient.getAddress());
        patient.setPhoneNumber(updatedPatient.getPhoneNumber());
        patient.setEmail(updatedPatient.getEmail());
        patient.setInsuranceProvider(updatedPatient.getInsuranceProvider());
        patient.setInsuranceNumber(updatedPatient.getInsuranceNumber());

        return patientRepository.save(patient);
    }

    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));

        patientRepository.delete(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    public List<Patient> findByFirstNameAndLastName(String firstName, String lastName) {
        return patientRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
