package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    //new added
    List<Doctor> findByFirstNameAndLastNameAndSpecialization(String firstName, String lastName, String specialization);
}