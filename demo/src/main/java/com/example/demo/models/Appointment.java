package com.example.demo.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    LocalDate date;
    LocalTime time;
    

    
}
