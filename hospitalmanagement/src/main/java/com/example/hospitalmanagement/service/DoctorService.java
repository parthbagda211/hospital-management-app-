package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.entity.Doctor;
import com.example.hospitalmanagement.exception.ResourceNotFoundException;
import com.example.hospitalmanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));
    }

    public Doctor updateDoctor(Long doctorId, Doctor updatedDoctor) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));

        doctor.setFirstName(updatedDoctor.getFirstName());
        doctor.setLastName(updatedDoctor.getLastName());
        doctor.setSpecialization(updatedDoctor.getSpecialization());
//        doctor.setPhoneNumber(updatedDoctor.getPhoneNumber());
//        doctor.setEmail(updatedDoctor.getEmail());

        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));

        doctorRepository.delete(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
 //new added
    public List<Doctor> findByNameOrSpecialization(String firstName, String lastName, String specialization) {
        return doctorRepository.findByFirstNameAndLastNameAndSpecialization(firstName, lastName, specialization);
    }

}
