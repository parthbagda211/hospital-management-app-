package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.Appointment;
import com.example.hospitalmanagement.entity.Doctor;
import com.example.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient(Patient patient);

//    List<Appointment> findByDoctor(Doctor doctor);
//    List<Appointment> findByPatientId(Long patientId);
//    List<Appointment> findByDoctorId(Long doctorId);
}