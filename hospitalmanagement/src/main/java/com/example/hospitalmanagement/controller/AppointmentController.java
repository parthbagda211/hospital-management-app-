package com.example.hospitalmanagement.controller;

import com.example.hospitalmanagement.entity.Appointment;
import com.example.hospitalmanagement.service.AppointmentService;
import com.example.hospitalmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  com.example.hospitalmanagement.service.DoctorService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
//
    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    @PostMapping("/addappointment")
    public ResponseEntity<Appointment> createAppointment(@RequestParam Long patientId, @RequestParam Long doctorId, @RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.createAppointment(patientId, doctorId, appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
    }

    @GetMapping("/{appointmentid}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long appointmentid) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentid);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/getallapp")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{appointmentid}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long appointmentid, @RequestBody Appointment appointment) {
        Appointment updatedAppointment = appointmentService.updateAppointment(appointmentid, appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{appointmentid}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long appointmentid) {
        appointmentService.deleteAppointment(appointmentid);
        return ResponseEntity.noContent().build();
    }
//    @GetMapping("/bypatient/{Id}")
//    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable Long Id) {
//        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(Id);
//        return ResponseEntity.ok(appointments);

//    @GetMapping("/doctor/{doctorId}")
//    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
//        List<Appointment> appointments = appointmentService.getDoctorById(doctorId);
//        return ResponseEntity.ok(appointments);
//   }

//    }
}
