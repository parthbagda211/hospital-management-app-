package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.entity.Consultation;
import com.example.hospitalmanagement.repository.ConsultationRepository;
import com.example.hospitalmanagement.entity.Appointment;
import com.example.hospitalmanagement.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final AppointmentService appointmentService;

    public ConsultationService(ConsultationRepository consultationRepository, AppointmentService appointmentService) {
        this.consultationRepository = consultationRepository;
        this.appointmentService = appointmentService;
    }

    public Consultation createConsultation(Long appointmentId, Consultation consultation) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        consultation.setAppointment(appointment);
        return consultationRepository.save(consultation);
    }

    public Consultation getConsultationByAppointment(Long appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        return consultationRepository.findByAppointment(appointment)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found for appointment: " + appointmentId));
    }

    public void deleteConsultation(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + id));
        consultationRepository.delete(consultation);
    }

    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    public Consultation updateConsultation(Long id, Consultation updatedConsultation) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + id));

        consultation.setConsultationNotes(updatedConsultation.getConsultationNotes());
        consultation.setPresctiption(updatedConsultation.getPresctiption());

        return consultationRepository.save(consultation);
    }
}
