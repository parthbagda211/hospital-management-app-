package com.example.hospitalmanagement.repository;

import com.example.hospitalmanagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import  java.util.*;
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}