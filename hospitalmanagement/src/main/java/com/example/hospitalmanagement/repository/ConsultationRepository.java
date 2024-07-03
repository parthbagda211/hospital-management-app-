package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.Appointment;
import com.example.hospitalmanagement.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Optional<Consultation> findByAppointment(Appointment appointment);
}