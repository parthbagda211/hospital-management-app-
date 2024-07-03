package com.example.hospitalmanagement.controller;
import com.example.hospitalmanagement.entity.Consultation;
import com.example.hospitalmanagement.service.ConsultationService;
import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hospitalmanagement.entity.Doctor;
import com.example.hospitalmanagement.exception.ResourceNotFoundException;
import com.example.hospitalmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/consultations")
public class ConsultationController {
    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping("/addconsultation")
    public ResponseEntity<Consultation> createConsultation(@RequestParam Long appointmentId, @RequestBody Consultation consultation) {
        Consultation savedConsultation = consultationService.createConsultation(appointmentId, consultation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConsultation);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Consultation> getConsultationByAppointment(@PathVariable Long appointmentId) {
        Consultation consultation = consultationService.getConsultationByAppointment(appointmentId);
        return ResponseEntity.ok(consultation);
    }

    @PutMapping("/{consultationid}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable Long consultationid, @RequestBody Consultation consultation) {
        Consultation updatedConsultation = consultationService.updateConsultation(consultationid, consultation);
        return ResponseEntity.ok(updatedConsultation);
    }

    @DeleteMapping("/{consultationid}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long consultationid) {
        consultationService.deleteConsultation(consultationid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getallconsultations")
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        List<Consultation> consultations = consultationService.getAllConsultations();
        return ResponseEntity.ok(consultations);
    }


}
