package com.example.demo.controllers;

import com.example.demo.models.Appointment;
import com.example.demo.repositories.AppointmentRepository;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/appointments")    

public class AppointmentController {
    @Autowired
    AppointmentRepository appointmentsRepository;

    @GetMapping("add-appointment")   
    public ModelAndView addAppointment() {
        ModelAndView mav = new ModelAndView("/doctors/addAppointments.html");
        Appointment appointment = new Appointment();
        mav.addObject("appointments", appointment);
        return mav;
    }
    
    @SuppressWarnings("null")
    @PostMapping("save-appointment")
    public String save(@ModelAttribute Appointment appointment) {
        this.appointmentsRepository.save(appointment);
        return "redirect:/appointments/add-appointment";
    }
    

    
}
