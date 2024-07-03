package com.example.hospitalmanagement.service;

import com.example.hospitalmanagement.entity.Appointment;
import com.example.hospitalmanagement.exception.ResourceNotFoundException;
import com.example.hospitalmanagement.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import com.example.hospitalmanagement.entity.Doctor;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
//    private  final Doctor doctor;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(Long patientId, Long doctorId, Appointment appointment) {

        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Long id) {

        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
    }

    public List<Appointment> getAllAppointments() {

        return appointmentRepository.findAll();
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        // Implement the logic to update an existing appointment
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));

        // Update the appointment fields with the new values
        appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        appointment.setAppointmentTime(updatedAppointment.getAppointmentTime());
//        appointment.setStatus(updatedAppointment.getStatus());

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));

        appointmentRepository.delete(appointment);
    }

//    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
//        return appointmentRepository.findByDoctorId(doctorId);
//    }


//    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
//        return appointmentRepository.findByPatientId(patientId);
//    }


}
