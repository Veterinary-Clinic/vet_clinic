package com.example.demo.controllers;

import com.example.demo.repositories.AppointmentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/appointments")    

public class AppointmentController {
    @Autowired
    AppointmentRepository appointmentsRepository;
    @GetMapping("/add-appointment")
    public void get() {
        System.out.println("sara");
    }
    
    
}
