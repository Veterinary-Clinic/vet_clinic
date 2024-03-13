package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    
}
