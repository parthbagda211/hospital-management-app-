package com.example.hospitalmanagement.entity;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultationId;
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    private String consultationNotes;
    private LocalDate consultationDate;
    private String presctiption;

    public Consultation(Long consultationId, Appointment appointment, String consultationNotes, String consultaionDate, String presctiption) {
        this.consultationId = consultationId;
        this.appointment = appointment;
        this.consultationNotes = consultationNotes;
        this.consultationDate = LocalDate.parse(consultaionDate);
        this.presctiption = presctiption;
    }

    public Consultation(){

    }
    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public LocalDate getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = LocalDate.parse(consultationDate);
    }

    // Getters, setters, and constructors

    public void setConsultationDate(LocalDate consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getPresctiption() {
        return presctiption;
    }

    public void setPresctiption(String prescription) {
        this.presctiption = prescription;
    }


}
